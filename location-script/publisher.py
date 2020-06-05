from amqpstorm import Connection
from amqpstorm import Message

connection = Connection('127.0.0.1', 'guest', 'guest')
channel = connection.channel()

# Message Properties.
properties = {
    'content_type': 'text/plain',
    'headers': {'key': 'value'}
}

# Create the message.
message = Message.create(channel=channel, body='Hello World!', properties=properties)

message.publish(routing_key='location.vehicleId')
