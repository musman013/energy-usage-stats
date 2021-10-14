import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Duration, SourceType, IEnergyUsageStats } from './Models';


@Injectable({
  providedIn: 'root',
})
export class EnergyService {
 
  url = environment.apiUrl + '/energyUsage';
  constructor(private http: HttpClient) {

  }

  getTotalCost() {
    return this.http.get(`${this.url}/getTotalCost`)
  }

  getEnergyUsageStats(duration: Duration, sourceTypes: SourceType[]) {
    return this.http.get<IEnergyUsageStats[]>(
      `${this.url}/getGraphData?duration=${duration}&sourceTypes=${sourceTypes}`);
  }
  
}
