export class LoggingService {

    errorLog(msg: String) {
        console.log('Error : ' + msg);
    }

    succesLog(msg: String) {
        console.log('Success : ' + msg);
    }
}
