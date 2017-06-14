package come.example.badge;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;


import java.util.Arrays;
import java.util.List;

import come.example.viewbadger.BadgerShortCut;
import come.example.viewbadger.ShortcutBadgeException;
import come.example.viewbadger.ShortcutBadger;

/**
 * Shortcut Badger support for Nova Launcher.

 */
public class NovaHomeBadger implements BadgerShortCut {

    private static final String CONTENT_URI = "content://com.teslacoilsw.notifier/unread_count";
    private static final String COUNT = "count";
    private static final String TAG = "tag";

    @Override
    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TAG, componentName.getPackageName() + "/" + componentName.getClassName());
            contentValues.put(COUNT, badgeCount);
            context.getContentResolver().insert(Uri.parse(CONTENT_URI), contentValues);
        } catch (IllegalArgumentException ex) {
            /* Fine, TeslaUnread is not installed. */
        } catch (Exception ex) {

            /* Some other error, possibly because the format
            of the ContentValues are incorrect. */

            throw new ShortcutBadgeException(ex.getMessage());
        }
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.teslacoilsw.launcher");
    }
}
