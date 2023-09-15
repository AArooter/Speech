package com.example.speech_to_text

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        val listener = object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                // Called when the recognizer is ready to receive speech input.
                Log.e("TAG", "onReadyForSpeech: ")
            }

            override fun onBeginningOfSpeech() {
                Log.e("TAG", "onBeginningOfSpeech: ")

                // Called when the user starts speaking.
                if (SpeechRecognizer.isRecognitionAvailable(this@MainActivity)) {
                    Log.e("TAG", "onBeginningOfSpeech: ")
                } else {
                    Log.e("TAG", "Error: ")
                }

            }

            override fun onRmsChanged(p0: Float) {
                Log.e("TAG", "onRmsChanged: ")
            }

            override fun onBufferReceived(p0: ByteArray?) {
                Log.e("TAG", "onBufferReceived: $p0")
            }

            override fun onEndOfSpeech() {
                // Called when the user stops speaking.
                Log.e("TAG", "onEndOfSpeech: ")
            }

            override fun onPartialResults(partialResults: Bundle?) {
                // Called with partial recognition results.
                Log.e("TAG", "onPartialResults: ")

            }

            override fun onResults(results: Bundle?) {
                // Called when the recognition is complete. Retrieve the recognized text from results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).

                Log.e("TAG", "onResults: $results")
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                Log.e("TAG", "onResults: $matches")

            }

            override fun onError(error: Int) {
                // Called when an error occurs during recognition. Handle errors here.
                Log.e("TAG", "onError: $error")
                return
            }

            override fun onEvent(eventType: Int, params: Bundle?) {
                // Called for various events during recognition.
                Log.e("TAG", "onEvent: ")
            }
        }

        speechRecognizer.setRecognitionListener(listener)

        val mic = findViewById<ImageView>(R.id.idIVMic)
        mic.setOnClickListener {

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

            speechRecognizer.startListening(intent)
        }


    }


}


