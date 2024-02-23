#Imports
from typing import List, Tuple
from typing import List, Tuple

def precision_recall(true_labels: List[bool], predicted_labels: List[bool]) -> Tuple[float, float]:
    """
    Compute the precision and recall of a series of predictions.

    Parameters
    ----------
    true_labels : List[bool]
        The actual results, i.e., the true labels.
    predicted_labels : List[bool]
        The predicted results that need to be evaluated.

    Returns
    -------
    Tuple[float, float]
        A tuple containing the precision and recall of the predicted results.
    """
    
    # Count the number of positive cases in the true labels
    positive_cases = sum(true_labels)
    
    # Calculate true positives (tp), false positives (fp), and false negatives (fn)
    tp = sum(1 for true, predicted in zip(true_labels, predicted_labels) if true and predicted) 
    fp = sum(1 for true, predicted in zip(true_labels, predicted_labels) if not true and predicted) 
    fn = positive_cases - tp 
    
    # Calculate precision and recall, handling the case where the denominator is zero
    precision = tp / (tp + fp) if (tp + fp) > 0 else 0.0
    recall = tp / (tp + fn) if (tp + fn) > 0 else 0.0

    return precision, recall


def F1_score(expected_results: List[bool], actual_results: List[bool]) -> float:
    """Compute the F1-score of a series of predictions

    Parameters
    ----------
    expected_results : List[bool]
        The true results, that is the results that the predictor
        should have found.
    actual_results : List[bool]
        The predicted results that have to be evaluated.

    Returns
    -------
    float
        The F1-score of the predicted results.
    """
    # Calculate precision and recall using the precision_recall function
    precision, recall = precision_recall(expected_results, actual_results)

    # Calculate F1-score using the slides
    f1_score = 2 * (precision * recall) / (precision + recall) if (precision + recall) > 0 else 0.0

    return f1_score
    raise NotImplementedError('Implement this method for Question 3')
