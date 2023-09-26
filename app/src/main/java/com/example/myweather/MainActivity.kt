package com.example.myweather

import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.example.myweather.databinding.ActivityMainBinding
import com.example.myweather.viewmodels.MainActivityViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


//I was going to do a very simple ui with compose consisting of a column and text but I had already
// did most of the project and when I added compose in everything broke I didnt think it was worth
//it to debug everything so here we are
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: MainActivityViewModel by viewModels()/*{
    See viewModel for details
        MainActivityViewModelFactory((application as WeatherApplication).repository)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dbRepository = (application as WeatherApplication).repository

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }
        viewModel.loadFromDb()
        setContentView(binding.root)
    }

    private var searchView: SearchView? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.searchview_menu, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        searchItem?.let {
            searchView = it.actionView as SearchView
            searchView!!.apply {
                val searchPlate =
                    this.findViewById(androidx.appcompat.R.id.search_src_text) as EditText

                searchPlate.hint = "Search"
                val searchPlateView: View = this.findViewById(androidx.appcompat.R.id.search_plate)
                searchPlateView.setBackgroundColor(
                    ContextCompat.getColor(this@MainActivity, android.R.color.transparent)
                )

                this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        //TODO do your logic here
                        //TODO only city works add country code and state
                        viewModel.searchForPlace(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                }
                )
                this.setOnCloseListener {
                    true
                }

                val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
                this.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (searchView?.isIconified == false) {
            searchView?.onActionViewCollapsed()
        } else {
            super.onBackPressed()
        }
    }


    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
               onLocationPermissionsAccepted()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                onLocationPermissionsAccepted()
            } else -> {
            // No location access granted.
        }
        }
    }

    @SuppressLint("MissingPermission")
    fun onLocationPermissionsAccepted(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                //TODO add lon and lat search in repository
            }

    }

// ...

// Before you perform the actual permission request, check whether your app
// already has the permissions, and whether your app needs to show a permission
// rationale dialog. For more details, see Request permissions.
}