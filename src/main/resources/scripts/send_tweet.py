#import dependencies
import tweepy
import sys

# variables for accessing twitter API
'''
lines = []
with open('/Users/michael/IdeaProjects/Tweety/src/main/resources/keys.txt') as f:
    lines = f.readlines()
consumer_key = lines[0]
consumer_key_secret = lines[1]
access_token = lines[2]
access_token_secret = lines[3]

print(consumer_key)
print(consumer_key_secret)
print(access_token)
print(access_token_secret)
'''

consumer_key = 'vB9lucuQkdZBOCRD62YKwifHB'
consumer_key_secret = 'eDnGKlfTMToDor8GOF1dlH7Sog5Jh7GEtz7N5l0ezRpEQO5S4W'
access_token = '1463004865135738887-r1tSYhsZ77yxfAqT90LN6ICGwtq8kd'
access_token_secret = 'faHWd6OiuPpnyitOkpAqvapLNolhmoo26jHFo61t5k4Wn'

# authenticating
auth = tweepy.OAuthHandler(consumer_key, consumer_key_secret)
auth.set_access_token(access_token, access_token_secret)
api = tweepy.API(auth)
api.update_status("this is a status message")

'''
if sys.argv[1] == 'none':
    message = ''
    for i in range(1, len(sys.argv)):
        message += sys.argv[i]
    api.update_status(message)
else:
    # text & media tweet
    message = ''
    for i in range(2, len(sys.argv)):
        message += sys.argv[i]
    api.update_with_media(sys.argv[1], message)
'''

