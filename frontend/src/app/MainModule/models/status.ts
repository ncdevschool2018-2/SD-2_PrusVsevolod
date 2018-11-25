export class Status {

  id: string;
  status: string;

  static cloneStatus(status: Status): Status{
    let cloneStatus: Status = new Status();
    cloneStatus.id = status.id;
    cloneStatus.status = status.status;
    return cloneStatus;
  }
}
