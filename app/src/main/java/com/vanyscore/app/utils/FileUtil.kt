package com.vanyscore.app.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object FileUtil {
    fun saveFileToInternalStorage(context: Context, uri: Uri, fileName: String): Uri? {
        var inputStream: InputStream? = null
        var outputStream: OutputStream? = null

        return try {
            // Get the content resolver to open the input stream
            val contentResolver: ContentResolver = context.contentResolver

            // Open the input stream from the URI
            inputStream = contentResolver.openInputStream(uri)

            // Create the output file in the internal storage directory
            val internalFile = File(context.filesDir, fileName)

            // Create an output stream to the internal file
            outputStream = FileOutputStream(internalFile)

            // Buffer to read the data
            val buffer = ByteArray(1024)
            var bytesRead: Int

            // Read from input stream and write to output stream
            while (inputStream?.read(buffer).also { bytesRead = it ?: -1 } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }

            // Successfully saved the file
            internalFile.toUri()
        } catch (e: IOException) {
            e.printStackTrace()
            // Failed to save the file
            null
        } finally {
            // Close streams
            try {
                inputStream?.close()
                outputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getFileExtensionFromUri(context: Context, uri: Uri): String? {
        // Get MIME type from the content resolver
        val mimeType = context.contentResolver.getType(uri)

        // Use MimeTypeMap to get the file extension based on the MIME type
        return mimeType?.let {
            MimeTypeMap.getSingleton().getExtensionFromMimeType(it)
        }
    }
}
