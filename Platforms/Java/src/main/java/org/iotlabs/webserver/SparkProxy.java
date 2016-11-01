package org.iotlabs.webserver;

import org.iotlabs.communication.mqtt.http.LedBluePrint;
import org.iotlabs.webserver.blueprints.AbstractBluePrint;
<<<<<<< HEAD
import org.iotlabs.webserver.blueprints.MessageBluePrint;
import org.iotlabs.webserver.blueprints.UsersBluePrint;
=======
import org.iotlabs.webserver.blueprints.AuthBluePrint;
import org.iotlabs.webserver.blueprints.RootBluePrint;
>>>>>>> 31cfe0c4acbae9b09ea10eb15fa49846a08b9f36

public class SparkProxy {
    private static class Holder {
        private static final SparkProxy SPARK_PROXY = new SparkProxy();
    }

    public static SparkProxy getInstance() {
        return Holder.SPARK_PROXY;
    }

    private SparkProxy() {
        new SparkInit();
    }

    public void run() {
        registerRouters();
    }

    /**
     * register default setted routers.
     */
    private void registerRouters() {
<<<<<<< HEAD
        new UsersBluePrint().register();
        new MessageBluePrint().register();
//        new MqttBluePrint().register();
        new LedBluePrint().register();
=======
        new AuthBluePrint().register();
        new RootBluePrint().register();
>>>>>>> 31cfe0c4acbae9b09ea10eb15fa49846a08b9f36
    }

    /**
     * register blueprint implementations
     * @param bluePrint router blueprint
     */
    public void register(AbstractBluePrint bluePrint) {
        bluePrint.register();
    }
}
