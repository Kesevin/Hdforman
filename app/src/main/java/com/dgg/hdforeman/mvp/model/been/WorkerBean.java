package com.dgg.hdforeman.mvp.model.been;

/**
 * Created by kelvin on 2016/11/3.
 */

public class WorkerBean {
    private int type;
    private Worker mWorker;
    private WorkerInfo mWorkerInfo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Worker getWorker() {
        return mWorker;
    }

    public void setWorker(Worker worker) {
        mWorker = worker;
    }

    public WorkerInfo getWorkerInfo() {
        return mWorkerInfo;
    }

    public void setWorkerInfo(WorkerInfo workerInfo) {
        mWorkerInfo = workerInfo;
    }
}
