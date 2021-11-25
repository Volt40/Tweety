#import dependencies
 import tweepy
import sys
from os.path import expanduser

# variables for accessing twitter API
lines = []
with open(expanduser("~") + "/.tweety") as f:
    lines = f.readlines()
consumer_key = lines[0][:-1]
consumer_key_secret = lines[1][:-1]
access_token = lines[2][:-1]
access_token_secret = lines[3]

# authenticating
auth = tweepy.OAuthHandler(consumer_key, consumer_key_secret)
auth.set_access_token(access_token, access_token_secret)
api = tweepy.API(auth)

if sys.argv[1] == 'none':
    message = ''
    for i in range(2, len(sys.argv)):
        message += sys.argv[i] + ' '
    api.update_status(message)
else:
    # text & media tweet
    message = ''
    for i in range(2, len(sys.argv)):
        message += sys.argv[i] + ' '
    api.update_with_media(sys.argv[1], message)