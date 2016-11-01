package org.iotlabs;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.iotlabs.communication.mqtt.MqttProxy;
import org.iotlabs.models.mqtt.recievers.Receiver;
import org.iotlabs.models.mqtt.recievers.Receivers;
import org.iotlabs.webserver.SparkProxy;

/**
 * the very entrance of IoTLabs
 */
public class Runner {
    private static final Logger logger = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.parsingArg(args);
    }

    private Options options = new Options();

    private void parsingArg(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();

        options.addOption(Option.builder("mqtt").hasArg(true).build());
        options.addOption(Option.builder("f").hasArg(true).build());
        options.addOption(Option.builder("server").hasArg(false).build());

        CommandLine cmd;
        try {
            cmd = commandLineParser.parse(options, args);
        } catch (ParseException e) {
            logger.info("Parse input arguments.", e);
            usage();
            return;
        }
        if (cmd.hasOption("mqtt")) {
            String mqttCommand = cmd.getOptionValue("mqtt");
            switch (mqttCommand) {
                case "start_broker":
                    MqttProxy.getInstance().startMqttBroker(null);
                    break;
                case "register_receivers":
                    if (cmd.hasOption("f")) {
                        String registerReceiverFilePath = cmd.getOptionValue("f");
                        Receivers receivers = MqttProxy.getInstance().getReceiversFromFile(registerReceiverFilePath);
                        if (receivers != null) {
                            MqttProxy.getInstance().registerReceivers(receivers, null, true);
                        }
                    } else {
                        usage();
                    }
                    break;
                default:
                    usage();
            }
        } else if (cmd.hasOption("server")) {
            SparkProxy.getInstance().run();
        } else {
            SparkProxy.getInstance().run();
            usage();
        }
    }



    private void usage() {
        System.out.println("Usage: -mqtt start_broker");
        System.out.println("Usage: -mqtt register_receivers -f receiver_preferences.json");
    }


}
