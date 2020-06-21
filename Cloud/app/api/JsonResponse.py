import json
import os
from http import HTTPStatus


def ok(body):
    return _json_response(body, HTTPStatus.OK)


def created(body):
    return _json_response(body, HTTPStatus.CREATED)


def accepted():
    return _json_response(None, HTTPStatus.ACCEPTED)


def no_content():
    return _json_response(None, HTTPStatus.NO_CONTENT)


def _json_response(body, status):
    return {
        "statusCode": status,
        "body": json.dumps(body),
        "headers": {
            "Content-Type": "application/json"
        }
    }
