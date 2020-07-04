from app.api.Response import Response
from app.repository.LocationRepo import LocationRepo

repo = LocationRepo()


def handle(event, context):
    id = str(event["queryStringParameters"]["id"])

    coords = repo.find(id)

    response = {
        "lat": int(coords["lat"]),
        "long": int(coords["long"])
    }

    return Response.ok(response)
