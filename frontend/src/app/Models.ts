export interface IEnergyUsage {
    hours: number;
    id: number;
    sourceType: SourceType;
    timestamp: Date;
    unitCost: number;
    units: number;
}

export interface IEnergyUsageStats {
    sourceType: SourceType;
    timestamp: Date;
    cost: number;
}

export enum SourceType {
    Coal = 'Coal',
    Gas = 'Gas',
    Electricity = 'Electricity'
}

export enum Duration {
    Month = 'month',
    Week = 'week'
}