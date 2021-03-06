import { DataSource } from "@angular/cdk/table";
import { BridgeResponse } from "../../../../../datatransferobjects/bridge.response";
import { Observable } from "rxjs/Observable";

export class MixingBridgeDatasource extends DataSource<any> {

    constructor(private msg: BridgeResponse[]) {
        super();
      }
    
      connect(): Observable<BridgeResponse[]> {
        return  Observable.of(this.msg);
      }
    
      disconnect() {}

}