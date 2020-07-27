package com.iterable.iterableapi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class IterableApiAuthTests extends BaseTest {

    private IterableUtil.IterableUtilImpl originalIterableUtil;
    private IterableUtil.IterableUtilImpl iterableUtilSpy;

    @Before
    public void setUp() {
        reInitIterableApi();

        originalIterableUtil = IterableUtil.instance;
        iterableUtilSpy = spy(originalIterableUtil);
        IterableUtil.instance = iterableUtilSpy;
    }

    @After
    public void tearDown() throws IOException {
        IterableUtil.instance = originalIterableUtil;
        iterableUtilSpy = null;
    }

    private void reInitIterableApi() {
        IterableApi.sharedInstance = spy(new IterableApi());
        IterableInAppManager inAppManagerMock = mock(IterableInAppManager.class);
        doReturn(inAppManagerMock).when(IterableApi.sharedInstance).getInAppManager();
    }

    @Test
    public void testSetEmailWithTokenStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String email = "test@example.com";
        String token = "token";

        IterableApi.getInstance().setEmail(email, token);

        assertEquals(IterableApi.getInstance().getEmail(), email);
        assertEquals(IterableApi.getInstance().getAuthToken(), token);
    }

    @Test
    public void testSetUserIdWithTokenStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String userId = "testUserId";
        String token = "token";

        IterableApi.getInstance().setUserId(userId, token);

        assertEquals(IterableApi.getInstance().getUserId(), userId);
        assertEquals(IterableApi.getInstance().getAuthToken(), token);
    }

    @Test
    public void testSameEmailWithNewTokenStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String email = "test@example.com";
        String token = "token";

        IterableApi.getInstance().setEmail(email, token);

        assertEquals(IterableApi.getInstance().getEmail(), email);
        assertEquals(IterableApi.getInstance().getAuthToken(), token);

        String newToken = "asdf";

        IterableApi.getInstance().setEmail(email, newToken);

        assertEquals(IterableApi.getInstance().getEmail(), email);
        assertEquals(IterableApi.getInstance().getAuthToken(), newToken);
    }

    @Test
    public void testSameUserIdWithNewTokenStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String userId = "testUserId";
        String token = "token";

        IterableApi.getInstance().setUserId(userId, token);

        assertEquals(IterableApi.getInstance().getUserId(), userId);
        assertEquals(IterableApi.getInstance().getAuthToken(), token);

        String newToken = "asdf";

        IterableApi.getInstance().setUserId(userId, newToken);

        assertEquals(IterableApi.getInstance().getUserId(), userId);
        assertEquals(IterableApi.getInstance().getAuthToken(), newToken);
    }

    @Test
    public void testSetSameEmailAndRemoveTokenStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String email = "test@example.com";
        String token = "token";

        IterableApi.getInstance().setEmail(email, token);

        assertEquals(IterableApi.getInstance().getEmail(), email);
        assertEquals(IterableApi.getInstance().getAuthToken(), token);

        IterableApi.getInstance().setEmail(email);

        assertEquals(IterableApi.getInstance().getEmail(), email);
        assertNull(IterableApi.getInstance().getAuthToken());
    }

    @Test
    public void testSetSameUserIdAndRemoveTokenStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String userId = "testUserId";
        String token = "token";

        IterableApi.getInstance().setUserId(userId, token);

        assertEquals(IterableApi.getInstance().getUserId(), userId);
        assertEquals(IterableApi.getInstance().getAuthToken(), token);

        IterableApi.getInstance().setUserId(userId);

        assertEquals(IterableApi.getInstance().getUserId(), userId);
        assertNull(IterableApi.getInstance().getAuthToken());
    }

    @Test
    public void testSetSameEmailStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String email = "test@example.com";

        IterableApi.getInstance().setEmail(email);

        assertEquals(IterableApi.getInstance().getEmail(), email);
        assertNull(IterableApi.getInstance().getUserId());
        assertNull(IterableApi.getInstance().getAuthToken());

        IterableApi.getInstance().setEmail(email);

        assertEquals(IterableApi.getInstance().getEmail(), email);
        assertNull(IterableApi.getInstance().getUserId());
        assertNull(IterableApi.getInstance().getAuthToken());
    }

    @Test
    public void testSetSameUserIdStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String userId = "testUserId";

        IterableApi.getInstance().setUserId(userId);

        assertNull(IterableApi.getInstance().getEmail());
        assertEquals(IterableApi.getInstance().getUserId(), userId);
        assertNull(IterableApi.getInstance().getAuthToken());

        IterableApi.getInstance().setUserId(userId);

        assertNull(IterableApi.getInstance().getEmail());
        assertEquals(IterableApi.getInstance().getUserId(), userId);
        assertNull(IterableApi.getInstance().getAuthToken());
    }

    @Test
    public void testSetSameEmailWithSameTokenStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String email = "test@example.com";
        String token = "token";

        IterableApi.getInstance().setEmail(email, token);

        assertEquals(IterableApi.getInstance().getEmail(), email);
        assertEquals(IterableApi.getInstance().getAuthToken(), token);

        IterableApi.getInstance().setEmail(email, token);

        assertEquals(IterableApi.getInstance().getEmail(), email);
        assertEquals(IterableApi.getInstance().getAuthToken(), token);
    }

    @Test
    public void testSetSameUserIdWithSameTokenStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String userId = "testUserId";
        String token = "token";

        IterableApi.getInstance().setUserId(userId, token);

        assertEquals(IterableApi.getInstance().getUserId(), userId);
        assertEquals(IterableApi.getInstance().getAuthToken(), token);

        IterableApi.getInstance().setUserId(userId, token);

        assertEquals(IterableApi.getInstance().getUserId(), userId);
        assertEquals(IterableApi.getInstance().getAuthToken(), token);
    }

    @Test
    public void testLogOutStatus() throws Exception {
        IterableApi.initialize(RuntimeEnvironment.application, "apiKey");

        String email = "test@example.com";

        IterableApi.getInstance().setEmail(email);

        assertEquals(IterableApi.getInstance().getEmail(), email);

        IterableApi.getInstance().setEmail(null);

        assertNull(IterableApi.getInstance().getEmail());
    }
}
