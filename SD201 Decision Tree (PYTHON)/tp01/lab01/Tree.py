#Imports
from typing import List
from PointSet import PointSet, FeaturesTypes
from typing import List
from PointSet import PointSet, FeaturesTypes
import numpy as np

class Tree:
    """A decision Tree

    Attributes
    ----------
    points : PointSet
        The training points of the tree
    """
    def __init__(self, features, labels, types, h=1, current_height=0, min_split_points=1, beta=0):
        """
        Initializes the decision tree.

        Parameters
        ----------
        features : List[List[float]]
            The features of the training points.
        labels : List[bool]
            The labels of the training points.
        types : List[FeaturesTypes]
            The types of each feature.
        h : int, optional
            The maximum height of the tree (default is 1).
        current_height : int, optional
            The current height of the tree (default is 0).
        min_split_points : int, optional
            The minimum number of points required to perform a split (default is 1).
        """
        self.points = PointSet(features, labels, types, min_split_points)  # Pass min_split_points to PointSet constructor
        self.left_child = None
        self.right_child = None
        self.split_index = None
        self.is_continuous = False
        self.min_split_points = min_split_points  # Added min_split_points as an attribute
        self.beta = beta  # Added beta as an attribute
        
        if current_height < h and len(labels) >= min_split_points:
            self.split_index, _ = self.points.get_best_gain()

            if self.split_index is not None:
                left_features = []
                left_labels = []
                right_features = []
                right_labels = []

                if types[self.split_index] == FeaturesTypes.REAL:
                    self.is_continuous = True

                    threshold = self.points.best_threshold

                    for i in range(len(features)):
                        if features[i][self.split_index] <= threshold:
                            left_features.append(features[i])
                            left_labels.append(labels[i])
                        else:
                            right_features.append(features[i])
                            right_labels.append(labels[i])
                else:
                    for i in range(len(features)):
                        if features[i][self.split_index] == self.points.best_value:
                            left_features.append(features[i])
                            left_labels.append(labels[i])
                        else:
                            right_features.append(features[i])
                            right_labels.append(labels[i])

                if len(left_features) >= min_split_points and len(right_features) >= min_split_points:
                    # Update the child nodes
                    self.left_child = Tree(left_features, left_labels, types, h, current_height=current_height + 1, min_split_points=min_split_points, beta=beta)
                    self.right_child = Tree(right_features, right_labels, types, h, current_height=current_height + 1, min_split_points=min_split_points, beta=beta)
                else:
                    # Update child nodes to None if the minimum split points condition is not met
                    self.left_child = None
                    self.right_child = None
            else:
                # Update child nodes to None if no more splits are possible
                self.left_child = None
                self.right_child = None

    def decide(self, features):
        """
        Give the guessed label of the tree to an unlabeled point.

        Parameters
        ----------
        features : List[float]
            The features of the unlabeled point.

        Returns
        -------
        bool
            The label of the unlabeled point,
            guessed by the Tree.
        """
        if self.left_child is not None and self.right_child is not None:
            if self.is_continuous:
                # Determine which child node to go to based on the continuous split
                child_node = self.left_child if features[self.split_index] <= self.points.best_threshold else self.right_child
            else:
                # Determine which child node to go to based on the categorical split
                child_node = self.left_child if features[self.split_index] == self.points.best_value else self.right_child

            # Recursively call decide on the chosen child node
            return child_node.decide(features)
        else:
            # If leaf node, return the majority label
            return bool(self.points.labels.sum() > len(self.points.labels) / 2)
        

    def add_training_point(self, features: List[float], label: bool) -> None:
        """
        Add a new training point to the tree.

        Parameters
        ----------
        features : List[float]
            The features of the new training point.
        label : bool
            The label of the new training point.

        Returns
        -------
        None
        """
        # Concatenate the new features and label to the existing features and labels
        self.points.features = np.concatenate((self.points.features, [features]), axis=0)
        self.points.labels = np.concatenate((self.points.labels, [label]), axis=0)

        # Check if the tree has left and right child nodes
        if self.left_child is not None and self.right_child is not None:
            if self.is_continuous:
                # Determine which child node to go to based on the continuous split
                child_node = self.left_child if features[self.split_index] <= self.points.best_threshold else self.right_child
            else:
                # Determine which child node to go to based on the categorical split
                child_node = self.left_child if features[self.split_index] == self.points.best_value else self.right_child

            # Recursively call add_training_point on the chosen child node
            child_node.add_training_point(features, label)

    def del_training_point(self, features: List[float], label: bool) -> None:
        """
        Remove a training point from the tree.

        Parameters
        ----------
        features : List[float]
            The features of the training point to be removed.
        label : bool
            The label of the training point to be removed.

        Returns
        -------
        None
        """
        # Find indices of points to be removed based on features and label
        indices_to_remove = np.where(np.all(self.points.features == features, axis=1) & (self.points.labels == label))[0]
        
        # Iterate through indices to remove and delete corresponding features and labels
        for index_to_remove in indices_to_remove:
            if index_to_remove < len(self.points.features):
                self.points.features = np.delete(self.points.features, index_to_remove, axis=0)
                self.points.labels = np.delete(self.points.labels, index_to_remove, axis=0)


        # Check if the tree has left and right child nodes
        if self.left_child is not None and self.right_child is not None:
            if self.is_continuous:
                # Determine which child node to go to based on the continuous split
                child_node = self.left_child if features[self.split_index] <= self.points.best_threshold else self.right_child
            else:
                # Determine which child node to go to based on the categorical split
                child_node = self.left_child if features[self.split_index] == self.points.best_value else self.right_child

            # Recursively call del_training_point on the chosen child node
            child_node.del_training_point(features, label)