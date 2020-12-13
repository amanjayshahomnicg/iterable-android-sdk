package com.iterable.iterableapi;

import com.iterable.iterableapi.unit.TestRunner;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

@RunWith(TestRunner.class)
public class IterableTaskRunnerTest {
    private IterableTaskRunner taskRunner;
    private IterableTaskStorage mockTaskStorage;
    private IterableActivityMonitor mockActivityMonitor;
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        mockTaskStorage = mock(IterableTaskStorage.class);
        mockActivityMonitor = mock(IterableActivityMonitor.class);
        taskRunner = new IterableTaskRunner(mockTaskStorage, mockActivityMonitor);
        server = new MockWebServer();
        IterableApi.overrideURLEndpointPath(server.url("").toString());
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void testRunOnTaskCreatedMakesApiRequest() throws Exception {
        IterableApiRequest request = new IterableApiRequest("apiKey", "api/test", new JSONObject(), "POST", null, null, null);
        IterableTask task = new IterableTask("testTask", IterableTaskType.API, request.toJSONObject().toString());
        when(mockTaskStorage.getNextScheduledTask()).thenReturn(task).thenReturn(null);
        taskRunner.onTaskCreated(null);
        runHandlerTasks(taskRunner);
        RecordedRequest recordedRequest = server.takeRequest(1, TimeUnit.SECONDS);
        assertNotNull(recordedRequest);
        assertEquals("/api/test", recordedRequest.getPath());
    }

    private void runHandlerTasks(IterableTaskRunner taskRunner) throws InterruptedException {
        shadowOf(taskRunner.handler.getLooper()).runToEndOfTasks();
    }
}
