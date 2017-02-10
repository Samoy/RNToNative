package com.rntonative;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "RNToNative";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 1 || resultCode != RESULT_OK) return;
        Uri contactData = data.getData();
        Cursor cursor = managedQuery(contactData, null, null, null, null);
        cursor.moveToFirst();
        String toRNMessage = this.getContactInfo(cursor);
        MainApplication.anExampleReactPackage.rnExampleInterface.sendMessage(toRNMessage);
    }

    private String getContactInfo(Cursor cursor) {
        String name = "";
        String phoneNumber = "";
        int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        String contactId = cursor.getString(idColumn);
        String queryString = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId;
        Uri aUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor phone = getContentResolver().query(aUri, null, queryString, null, null);
        String dn = ContactsContract.Contacts.DISPLAY_NAME;
        String pn = ContactsContract.CommonDataKinds.Phone.NUMBER;
        if (phone != null && phone.moveToFirst()) {
            for (; !phone.isAfterLast(); phone.moveToNext()) {
                dn =
                        name = cursor.getString(cursor.getColumnIndex(dn));
                phoneNumber = phone.getString(phone.getColumnIndex(pn));
            }
            phone.close();
        }
        return "{\"msgType\":\"pickContactResult\",\"displayname:\"" + name + "\",\"phoneNumber\":\"" + phoneNumber + "\"}";
    }
}
