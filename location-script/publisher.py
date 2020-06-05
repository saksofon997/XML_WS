import pika
import sys
import json

connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost', port='5672'))
channel = connection.channel()

channel.queue_declare(queue='location', durable=False)

message = {"vehicleId": 12345,
           "message": "neki tekst"}

channel.basic_publish(exchange='location-exchange',
                      routing_key='location.baz',
                      body=json.dumps(message))

print(f"Message sent: {message}")

connection.close()
