package mx.edu.utez.mediarecorder.data;

import static android.os.Looper.prepare;

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import java.io.File

import kotlin.Suppress;

class AudioRecorder(private val context: Context) {

    private var recorder: MediaRecorder? = null

    private fun createRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            @Suppress("DEPRECATION")
                    MediaRecorder()
        }
    }

    fun start(outputFile: File) {
        stop()

        recorder = createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(outputFile.absolutePath)

            try {
                prepare()
                start()
            } catch (e: Exception) {
                e.printStackTrace()
                recorder?.release()
                recorder = null
            }
        }
    }

    fun stop() {
        try {
            recorder?.stop()
            recorder?.release()
        } catch (e: Exception) {
            e.printStackTrace()
            recorder?.release()
        }
        recorder = null
    }
}
