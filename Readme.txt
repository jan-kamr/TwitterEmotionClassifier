Files in Twitter Emotion Classifier Java Project:

Main: Instantiates a Corpus object with the absolute path of the directory of input files

Corpus: Reads the input files and encapsulates the data into list of tweets. It also evaluates the statistical measurements of the model.

Tweet: Contains all information of a tweet. TaggedCorrectly() method compares gold tag with predicted tag. Evaluator() method counts number of TP, FP, TN, and Fns for different emotions. FeatureExtraction() method is used later for classification.

Result: Contains TP, FP, TN, and FN as attributes and calculates precision and recall based upon.

