package krasnikov.project.postsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import krasnikov.project.postsapp.data.Result
import krasnikov.project.postsapp.data.repository.PostRepository
import krasnikov.project.postsapp.data.source.remote.RemotePostDataSource
import krasnikov.project.postsapp.data.source.remote.api.PostService
import krasnikov.project.postsapp.utils.AppExecutor
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}