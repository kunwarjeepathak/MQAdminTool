/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.Connection;

import MQApi.Models.Query.ConnectionDetailModel;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQExternalSecurityExit;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;
import java.util.Hashtable;
import java.util.Properties;

/**
 *
 * @author jzhou
 */
public class MQConnection {
     public static MQQueueManager GetMQQueueManager(ConnectionDetailModel connectionDetail) throws MQException{
        Properties properties = new Properties();
        properties.put(MQConstants.HOST_NAME_PROPERTY, connectionDetail.Host);
        properties.put(MQConstants.PORT_PROPERTY, Integer.parseInt(connectionDetail.Port));
        properties.put(MQConstants.CHANNEL_PROPERTY, connectionDetail.Channel);
        properties.put("transport", "MQSeries");
        //properties.put("securityExit", new MQExternalSecurityExit() ); //for MQ ver < 7
        properties.put("securityExit", new SecurityExit(null, null)); //for MQ ver < 7
        MQQueueManager queueManager = new MQQueueManager(connectionDetail.QueueManager, properties);
        MQQueue commandQueue = queueManager.accessQueue(queueManager.getCommandInputQueueName(), CMQC.MQOO_INQUIRE);
        if(commandQueue.getOpenInputCount() == 0){
            throw new MQException(2, 1, "Command server is not running on this queue manager");
        }
        return queueManager;
    }   
}
