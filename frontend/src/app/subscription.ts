export class Subscription {
  id: number;
  channelName: string;
  channelUrl: string;
  subscribedDate: string; // Mantenha como string para compatibilidade com o backend

  constructor(
    id: number = 0,
    channelName: string = '',
    channelUrl: string = '',
    subscribedDate: string = ''
  ) {
    this.id = id;
    this.channelName = channelName;
    this.channelUrl = channelUrl;
    this.subscribedDate = subscribedDate;
  }
}
