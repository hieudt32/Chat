package vn.iotech.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * EventBusWrapper
 * Created by akai on 12/22/2017.
 */

public class EventBusWrapper {

    public EventBusWrapper() {

    }

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }
}
