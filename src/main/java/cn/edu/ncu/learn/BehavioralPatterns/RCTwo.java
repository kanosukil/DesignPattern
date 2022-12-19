package cn.edu.ncu.learn.BehavioralPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 一部手机，在其接受到来电的时候，会发出声音来提醒主人。
 * 而现在我们需要为该手机添加一项功能，在接收来电的时候，产生震动，
 * <p>
 * 为令其更加高级，不仅发声，而且振动，而且有灯光闪烁。
 */

abstract class CallReminder {
    protected CallReminder nextReminder;

    public void setNextReminder(CallReminder reminder) {
        nextReminder = reminder;
    }

    public void remind() {
        action();
        if (nextReminder != null) {
            nextReminder.remind();
        }
    }

    protected abstract void action();
}

class VoiceReminder extends CallReminder {

    public VoiceReminder(CallReminder next) {
        nextReminder = next;
    }

    @Override
    protected void action() {
        System.out.println("Using the music reminds user.");
    }
}

class LightReminder extends CallReminder {

    public LightReminder(CallReminder next) {
        nextReminder = next;
    }

    @Override
    protected void action() {
        System.out.println("Using the screen reminds user.");
    }
}

class VibrationReminder extends CallReminder {

    public VibrationReminder(CallReminder next) {
        nextReminder = next;
    }

    @Override
    protected void action() {
        System.out.println("Using the vibration reminds user.");
    }
}

public class RCTwo implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 10: ");
        new VibrationReminder(new LightReminder(new VoiceReminder(null))).remind();
    }
}
