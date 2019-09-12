package pl.arturostrowski.android.mvp.app.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pl.arturostrowski.android.mvp.app.di.component.DaggerActivityComponent
import pl.arturostrowski.android.mvp.app.di.module.ActivityModule
import pl.arturostrowski.android.mvp.app.ui.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.os.PersistableBundle
import pl.arturostrowski.android.mvp.app.R


class MainActivity: AppCompatActivity(), MainContract.View {

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
//        toolbar_title.text = toolbar.title

        supportActionBar!!.setDisplayShowTitleEnabled(false)

        injectDependency()
        presenter.attach(this)
        changeScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    override fun setTitle(title: String) {
        toolbar_title.text = title
        showToolbar(true)
    }

    override fun showToolbar(show: Boolean) {
        appBarLayout.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun changeScreenOrientation(screenOrientation: Int) {
        requestedOrientation = screenOrientation
    }

    override fun showDashboardFragment() {
        supportFragmentManager.beginTransaction()
                .addToBackStack(DashboardFragment.TAG)
                .setCustomAnimations(AnimType.SLIDE.getAnimPair().first, AnimType.SLIDE.getAnimPair().second)
                .replace(R.id.mainContainer, DashboardFragment.newInstance(), DashboardFragment.TAG)
                .commit()
    }

    fun switchContent(fragment: Fragment, fragmentTag: String){
        supportFragmentManager.beginTransaction()
                .addToBackStack(fragmentTag)
                .setCustomAnimations(AnimType.SLIDE.getAnimPair().first, AnimType.SLIDE.getAnimPair().second)
                .replace(R.id.mainContainer, fragment, fragmentTag)
                .commit()
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when(item!!.itemId) {
//            R.id.nav_item_info -> {
//                presenter.onDrawerOptionAboutClick()
//                return true
//            }
//            else -> {
//
//            }
//        }
//
//        return super.onOptionsItemSelected(item)
//    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(DashboardFragment.TAG)

        if (fragment == null) {
//            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }

    enum class AnimType() {
        SLIDE,
        FADE;

        fun getAnimPair(): Pair<Int, Int> {
            when(this) {
                SLIDE -> return Pair(R.anim.slide_left, R.anim.slide_right)
                FADE -> return Pair(R.anim.fade_in, R.anim.fade_out)
            }

            return Pair(R.anim.slide_left, R.anim.slide_right)
        }
    }
}