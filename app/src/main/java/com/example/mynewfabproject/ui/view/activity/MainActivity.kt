package com.example.mynewfabproject.ui.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mynewfabproject.R

import com.example.mynewfabproject.databinding.ActivityMainBinding
import com.example.mynewfabproject.di.HiltApplication.Companion.CHANNEL_ID
import com.example.mynewfabproject.ui.adapter.MovieAdapter
import com.example.mynewfabproject.ui.adapter.MovieClickListener
import com.example.mynewfabproject.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        MovieAdapter(object : MovieClickListener {
            override fun movieClickListener(imdbId: String) {

                val intent=Intent(this@MainActivity, MovieDetailActivity::class.java)
                intent.putExtra("imdbId",imdbId)
                startActivity(intent)
            }

        }, emptyList())
    }
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        binding.searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun onQueryTextSubmit(query: String): Boolean {

                // remote views // bildiris idare etme ile baglidi//

                if (query.trim() != "") {
                    viewModel.searchMovie(query)

                    // test notification
                    val notification=NotificationCompat.Builder(this@MainActivity,CHANNEL_ID)
                        .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background))
                        .setContentTitle("Search Movie")
                        .setContentText("searchiing")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .build()

                    val permission = Manifest.permission.POST_NOTIFICATIONS
                    val myrequestCode = 123

                    if (ActivityCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            this@MainActivity,
                            arrayOf(permission),
                            myrequestCode
                        )
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return true
                    }else{
                        NotificationManagerCompat.from(this@MainActivity).notify(0,notification)
                    }



                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                if (newText.isEmpty()) {
                    adapter.update(emptyList())
                }
                return true
            }

        })
        binding.rv.layoutManager = GridLayoutManager(this, 2)
        binding.rv.adapter = adapter

        viewModel.moviesLiveData.observe(this) {
            adapter.update(it)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // İzin verildiyse, işlemlerinizi burada gerçekleştirin
                // ...
            } else {
                // İzin reddedildiyse veya iptal edildiyse, kullanıcıya geri bildirimde bulunabilirsiniz
                // ...
            }
        }
    }



}