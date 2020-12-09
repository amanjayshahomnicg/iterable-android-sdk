package com.iterable.iterableapi;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

class IterableTask {

    //String columns as stored in DB
    static final String TASK_ID = "task_id";
    static final String NAME = "name";
    static final String VERSION = "version";
    static final String CREATED_AT = "created";
    static final String MODIFIED_AT = "modified";
    static final String LAST_ATTEMPTED_AT = "last_attempt";
    static final String SCHEDULED_AT = "scheduled";
    static final String REQUESTED_AT = "requested";
    static final String PROCESSING = "processing";
    static final String FAILED = "failed";
    static final String BLOCKING = "blocking";
    static final String DATA = "data";
    static final String ERROR = "error";
    static final String TYPE = "type";
    static final String ATTEMPTS = "attempts";

    int currentVersion = 1;

    String id;
    String name;
    int version;
    Date createdAt;
    Date modifiedAt;
    Date lastAttemptedAt;
    Date scheduledAt;
    Date requestedAt;

    boolean processing;
    boolean failed;
    boolean blocking;

    //TODO: Confirm if data and failure data would be String converted from JSONObjects.
    String data;
    String taskFailureData;
    IterableTaskType taskType;
    int attempts;

    //To be used when creating IterableTask from database
    IterableTask(String id, @NonNull String name, int version, @NonNull Date createdAt, Date modifiedAt, Date lastAttemptedAt, Date scheduledAt, Date requestedAt, boolean processing, boolean failed, boolean blocking, String data, String taskFailureData, IterableTaskType taskType, int attempts) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.lastAttemptedAt = lastAttemptedAt;
        this.scheduledAt = scheduledAt;
        this.requestedAt = requestedAt;
        this.processing = processing;
        this.failed = failed;
        this.blocking = blocking;
        this.data = data;
        this.taskFailureData = taskFailureData;
        this.taskType = taskType;
        this.attempts = attempts;
    }

    //Bare minimum one to be used when creating the Task
    IterableTask(String name, IterableTaskType taskType, String data) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.createdAt = new Date();
        this.scheduledAt = new Date();
        this.requestedAt = new Date();
        this.data = data;
        this.taskType = taskType;
    }

}

enum IterableTaskType {
    API {
        @Override
        @NonNull
        public String toString() {
            return "API";
        }
    }
}