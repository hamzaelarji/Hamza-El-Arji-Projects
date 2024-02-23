#Imports
from typing import List, Tuple
import numpy as np
from enum import Enum


class FeaturesTypes(Enum):
    """Enumerate possible features types"""
    BOOLEAN = 0
    CLASSES = 1
    REAL = 2

class PointSet:
    """A class representing a set of training points.

    Attributes
    ----------
    types : List[FeaturesTypes]
        Each element of this list is the type of one of the
        features of each point.
    features : np.array[float]
        2D array containing the features of the points. Each line
        corresponds to a point, each column to a feature.
    labels : np.array[bool]
        1D array containing the labels of the points.
    """
    def __init__(self, features: List[List[float]], labels: List[bool], types: List[FeaturesTypes], min_split_points: int = 1):
        """
        Initializes the PointSet.

        Parameters
        ----------
        features : List[List[float]]
            The features of the points. Each sublist contained in the
            list represents a point, each of its elements is a feature
            of the point. All the sublists should have the same size as
            the `types` parameter, and the list itself should have the
            same size as the `labels` parameter.
        labels : List[bool]
            The labels of the points.
        types : List[FeaturesTypes]
            The types of the features of the points.
        min_split_points : int, optional
            The minimum number of points required to perform a split (default is 1).
        """
        self.types = types                          # `self.types` stores the list of feature types for each point.
        self.features = np.array(features)          # `self.features` is a 2D NumPy array that stores the features of the points. Each row corresponds to a point, and each column corresponds to a feature.
        self.labels = np.array(labels)              # `self.labels` is a 1D NumPy array that stores the labels of the points.
        self.best_value = None                      # `self.best_value` is used to store the best value determined during tree splitting.
        self.best_feature_id = None                 # `self.best_feature_id` stores the index of the feature that gives the best split in the tree.
        self.best_gini_gain = None                  # `self.best_gini_gain` stores the Gini gain achieved by the best split.
        self.best_threshold = None                  # `self.best_threshold` stores the threshold value used for the best split. Relevant for continuous (real) features.
        self.min_split_points = min_split_points    # `self.min_split_points` is the minimum number of points required to perform a split.
   
    
    def get_gini(self) -> float:
        """Computes the Gini score of the set of points

        Returns
        -------
        float
            The Gini score of the set of points
        """
        # Check if there are no labels (points) in the set. 
        if len(self.labels) == 0:
            return 0.0
        
        # Calculate the number of points, count of true labels, and count of false labels
        n = len(self.labels)
        true_count = np.sum(self.labels)
        false_count = n - true_count
        
        # Calculate the probabilities of having true or false labels in the set
        p_true = true_count / n
        p_false = false_count / n
        
        # Compute and return the Gini score using the calculated probabilities
        gini = 1 - (p_true ** 2) - (p_false ** 2)
        return gini
    

    def get_best_gain(self) -> Tuple[int, float]:
        """
        Finds the feature and the Gini gain for the best split.

        Returns
        -------
        Tuple[int, float]
            The best feature index and its corresponding Gini gain.
        """
        # Initialize variables
        best_feature_id = None
        best_gini_gain = None
        best_threshold = None
        base_gini = self.get_gini()    # Calculate the Gini score of the current set of points

        # Iterate over each feature to find the best split
        for feature_id in range(self.features.shape[1]):
            unique_values = np.unique(self.features[:, feature_id])
            
             # Check the type of the feature for different handling
            if self.types[feature_id] == FeaturesTypes.BOOLEAN or self.types[feature_id] == FeaturesTypes.CLASSES:
                for i, value in enumerate(unique_values):
                    current_subset = self.features[:, feature_id] == value
                    left_set = PointSet(self.features[current_subset], self.labels[current_subset], self.types, self.min_split_points)
                    right_set = PointSet(self.features[~current_subset], self.labels[~current_subset], self.types, self.min_split_points)

                    # Check if the split meets the min split points condition
                    if len(left_set.labels) < self.min_split_points or len(right_set.labels) < self.min_split_points:
                        continue

                    left_gini_score = left_set.get_gini()
                    right_gini_score = right_set.get_gini()
                    total_size = self.labels.size
                    left_size = left_set.labels.size
                    right_size = right_set.labels.size
                    
                    # Calculate the Gini gain for the current split
                    current_gini_gain = base_gini - (left_size / total_size) * left_gini_score - (right_size / total_size) * right_gini_score
                    
                    # Update if the current split provides a higher Gini gain
                    if best_gini_gain is None or current_gini_gain > best_gini_gain:
                        best_gini_gain = current_gini_gain
                        best_feature_id = feature_id
                        self.best_value = value
            else:
                sorted_features = np.sort(self.features[:, feature_id])
                for i in range(len(sorted_features) - 1):
                    if sorted_features[i] != sorted_features[i + 1]:
                        current_threshold = (sorted_features[i] + sorted_features[i + 1]) / 2
                        current_subset = self.features[:, feature_id] <= current_threshold
                        left_set = PointSet(self.features[current_subset], self.labels[current_subset], self.types, self.min_split_points)
                        right_set = PointSet(self.features[~current_subset], self.labels[~current_subset], self.types, self.min_split_points)

                        # Check if the split meets the min split points condition
                        if len(left_set.labels) < self.min_split_points or len(right_set.labels) < self.min_split_points:
                            continue

                        left_gini_score = left_set.get_gini()
                        right_gini_score = right_set.get_gini()
                        total_size = self.labels.size
                        left_size = left_set.labels.size
                        right_size = right_set.labels.size
                        
                        # Calculate the Gini gain for the current split
                        current_gini_gain = base_gini - (left_size / total_size) * left_gini_score - (right_size / total_size) * right_gini_score
                        
                        # Update if the current split provides a higher Gini gain
                        if best_gini_gain is None or current_gini_gain > best_gini_gain:
                            best_gini_gain = current_gini_gain
                            best_feature_id = feature_id
                            best_threshold = current_threshold

        # Update attributes with information about the best split
        self.best_gini_gain = best_gini_gain
        self.best_feature_id = best_feature_id
        self.best_threshold = best_threshold

        return best_feature_id, best_gini_gain


    def get_best_threshold(self) -> float:
        """
        Gets the best threshold for the selected feature.

        Returns
        -------
        float
            The best threshold for the selected feature.
        """
        # Check if get_best_gain() has been called to set the best_feature_id
        if self.best_feature_id is None:
            raise Exception("get_best_gain() must be called before get_best_threshold()")
        
        # Check the type of the selected feature for threshold availability
        if self.types[self.best_feature_id] == FeaturesTypes.BOOLEAN or self.types[self.best_feature_id] == FeaturesTypes.CLASSES:
            return None # Threshold is not applicable for BOOLEAN or CLASSES types
        else:
            return self.best_threshold # Return the best threshold for the selected feature