import { Model } from './Model.model';

export class Brand {
  id: number;
  name: string;
  models: Model[];
  constructor(id: number, name: string, models: Model[]){
    this.id = id;
    this.name = name;
    this.models = models;
  }
}
