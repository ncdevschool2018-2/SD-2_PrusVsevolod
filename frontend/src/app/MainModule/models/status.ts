export class Status {

  id: string;
  name: string;

  static cloneStatus(status: Status): Status{
    let cloneStatus: Status = new Status();
    cloneStatus.id = status.id;
    cloneStatus.name = status.name;
    return cloneStatus;
  }
}
