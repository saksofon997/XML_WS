# example_publisher.py
import pika, os, logging
logging.basicConfig()

# Parse CLODUAMQP_URL (fallback to localhost)
url = 'localhost:5672'
params = pika.URLParameters(url)
params.socket_timeout = 5

connection = pika.BlockingConnection(params) # Connect to CloudAMQP
channel = connection.channel() # start a channel
channel.queue_declare(queue='location') # Declare a queue
# send a message

channel.basic_publish(exchange='location-exchange', routing_key='location.1234', body='Nova lokacija')
print ("[x] Message sent to consumer")
connection.close()
