export class VehicleOccupancy {
    id: number;
    startTime: number;
    endTime: number;
    type: string;
    locations: string;

    constructor(id: number, startTime: number, endTime: number, type: string, locations: string) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.locations = locations;
    }
}
