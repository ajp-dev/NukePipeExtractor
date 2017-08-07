package org.schabi.newpipe.extractor.services.youtube;

import org.junit.Before;
import org.junit.Test;
import org.schabi.newpipe.Downloader;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.playlist.PlaylistExtractor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.schabi.newpipe.extractor.ServiceList.YouTube;

/**
 * Test for {@link PlaylistExtractor}
 */

public class YoutubePlaylistExtractorTest {
    private PlaylistExtractor extractor;

    @Before
    public void setUp() throws Exception {
        NewPipe.init(Downloader.getInstance());
        extractor = YouTube.getService()
                .getPlaylistExtractor("https://www.youtube.com/playlist?list=PL7XlqX4npddfrdpMCxBnNZXg2GFll7t5y");
    }

    @Test
    public void testGetDownloader()  throws Exception {
        assertNotNull(NewPipe.getDownloader());
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(extractor.getPlaylistId(), "PL7XlqX4npddfrdpMCxBnNZXg2GFll7t5y");
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(extractor.getPlaylistName(), "important videos");
    }

    @Test
    public void testGetAvatarUrl() throws Exception {
        assertTrue(extractor.getAvatarUrl(), extractor.getAvatarUrl().contains("yt"));
    }

    @Test
    public void testGetBannerUrl() throws Exception {
        assertTrue(extractor.getBannerUrl(), extractor.getBannerUrl().contains("yt"));
    }

    @Test
    public void testGetUploaderUrl() throws Exception {
        assertTrue(extractor.getUploaderUrl(), extractor.getUploaderUrl().contains("youtube.com"));
    }

    @Test
    public void testGetUploaderName() throws Exception {
        assertTrue(extractor.getUploaderName(), !extractor.getUploaderName().isEmpty());
    }

    @Test
    public void testGetUploaderAvatarUrl() throws Exception {
        assertTrue(extractor.getUploaderAvatarUrl(), extractor.getUploaderAvatarUrl().contains("yt"));
    }

    @Test
    public void testGetStreamsCount() throws Exception {
        assertTrue("error in the streams count", extractor.getStreamCount() > 100);
    }

    @Test
    public void testGetStreams() throws Exception {
        assertTrue("no streams are received", !extractor.getStreams().getItemList().isEmpty());
    }

    @Test
    public void testGetStreamsErrors() throws Exception {
        assertTrue("errors during stream list extraction", extractor.getStreams().getErrors().isEmpty());
    }

    @Test
    public void testHasMoreStreams() throws Exception {
        // Setup the streams
        extractor.getStreams();
        assertTrue("extractor didn't have more streams", extractor.hasMoreStreams());
    }

    @Test
    public void testGetNextStreams() throws Exception {
        // Setup the streams
        extractor.getStreams();
        assertTrue("extractor didn't have next streams", !extractor.getNextStreams().nextItemsList.isEmpty());
        assertTrue("extractor didn't have more streams after getNextStreams", extractor.hasMoreStreams());
    }

}
