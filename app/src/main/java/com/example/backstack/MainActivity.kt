package com.example.backstack


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


lateinit var bottomNavigationView:BottomNavigationView
class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
//    lateinit var  fragmentManager:FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragments(FeedFragment())
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setOnItemSelectedListener(this)
//        fragmentManager.addOnBackStackChangedListener(this)


    }

    fun loadFragments(fragment:Fragment?):Boolean{

        if(fragment != null){
             getSupportFragmentManager().beginTransaction().
             replace(R.id.parentFrame,fragment).
             addToBackStack(null)
             .commit()

        }
        return true

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment:Fragment? = null
        when(item.itemId){
            R.id.feedFragment -> fragment = FeedFragment()
            R.id.videosFragment -> fragment = VideosFragment()
            R.id.exploreFragment -> fragment = ExploreFragment()
        }
        return loadFragments(fragment)
    }


    override fun onBackPressed() {

        val count = getSupportFragmentManager().backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        }
        else {
            val index = getSupportFragmentManager().backStackEntryCount - 1
            getSupportFragmentManager().popBackStack()
            val backEntry = getSupportFragmentManager().getBackStackEntryAt(index)
            val stackId = backEntry.id
            bottomNavigationView.menu.getItem(stackId).isChecked = true
        }
    }

//    override fun onBackStackChanged() {
//
//            val bse = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1)
//            //bse will be the backstack entry for current fragment
//            if (bse.name == FeedFragment::class.java.getName()) {
//                bottomNavigationView.getMenu().getItem(0).setChecked(true)
//            } else if (bse.name == VideosFragment::class.java.getName()) {
//                bottomNavigationView.getMenu().getItem(1).setChecked(true)
//            }
//            else if (bse.name == ExploreFragment::class.java.getName()) {
//                bottomNavigationView.getMenu().getItem(2).setChecked(true)
//            }
//        }



}