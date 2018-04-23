package com.silence.music.file;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.silence.music.bean.MusicBean;
import com.silence.music.utils.ClearUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Silence
 * @date 2018/4/23
 */
public class MusicSupport {

    @SuppressLint("StaticFieldLeak")
    private static MusicSupport mInstance;
    private Context context;

    private MusicSupport(Context context) {
        this.context = context;
    }

    public static MusicSupport getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ClearUtils.class) {
                if (mInstance == null) {
                    mInstance = new MusicSupport(context);
                }
            }
        }
        return mInstance;
    }

    public List<MusicBean> getMusic() {
        ContentResolver contentResolver = context.getContentResolver();
        @SuppressLint("Recycle") Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        List<MusicBean> musicBeanList = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {
            //新建一个歌曲对象,将从cursor里读出的信息存放进去,直到取完cursor里面的内容为止.
            MusicBean musicBean = new MusicBean();
            cursor.moveToNext();
            long id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));   //音乐id

            String title = cursor.getString((cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题

            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家

            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));//时长

            long size = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE));  //文件大小

            String url = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));  //文件路径

            String album = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM)); //唱片图片

            long album_id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)); //唱片图片ID

            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐
            if (isMusic != 0) {
                musicBean.setMusicId(id);
                musicBean.setMusicTitle(title);
                musicBean.setArtist(artist);
                musicBean.setDuration(duration);
                musicBean.setSize(size);
                musicBean.setUrl(url);
                musicBean.setAlbum(album);
                musicBean.setAlbumId(album_id);
                musicBeanList.add(musicBean);
            }
        }
        return musicBeanList;
    }
}