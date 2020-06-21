import json

from app.api import JsonResponse
from app.external.MailProxy import MailProxy


def send(event, context):
    if event["queryStringParameters"]["security"] == "picici2":

        data = json.loads(event["body"])
        print(data)

        address = data["address"]
        message = data["message"]

        MailProxy().send(toaddrs=address, message=message)

        return JsonResponse.accepted()

    return JsonResponse.no_content()
