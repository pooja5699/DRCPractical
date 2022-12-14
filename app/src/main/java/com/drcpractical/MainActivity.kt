package com.drcpractical

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.drcpractical.databinding.ActivityMainBinding
import com.drcpractical.network.model.HomeRequest
import com.drcpractical.network.util.ApiException
import com.drcpractical.network.util.DialogProgress
import com.drcpractical.network.util.NoInternetException
import com.drcpractical.network.util.showToast
import com.drcpractical.network.viewmodel.DashboardViewModel
import com.drcpractical.network.viewmodel.DashboardViewModelFactory
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: DashboardViewModelFactory by instance()
    private lateinit var viewModel: DashboardViewModel

    //Binding Controls
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
    val PERIOD_MS: Long = 3000 // time in milliseconds between successive task executions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory).get(DashboardViewModel::class.java)
        homePage()
    }

    private fun homePage() {
        showLoading()
        val request = HomeRequest(customerId = "")
        lifecycleScope.launch {
            try {
                val response = viewModel.hopePage(request)
                if (response.isNotEmpty()) {

                    val bestSellerAdapter = BestSellerAdapter()
                    binding.rvBestSeller.adapter = bestSellerAdapter
                    response[0].data?.bestSeller?.bestsellerList?.let {
                        bestSellerAdapter.setTitle(
                            it
                        )
                    }

                    val adapter = BannerSliderAdapter()
                    binding.viewPagerBanner.adapter = adapter
                    response[0].data?.bannerSlider?.let { adapter.setTitle(it) }
                    binding.dotsIndicator.setViewPager2(binding.viewPagerBanner)

                    val handler = Handler(Looper.getMainLooper())
                    val update = Runnable {
                        if (currentPage == binding.viewPagerBanner.size - 1) {
                            currentPage = 0
                        }
                        binding.viewPagerBanner.setCurrentItem(currentPage++, true)
                    }

                    timer = Timer() // This will create a new Thread
                    timer?.schedule(object : TimerTask() {
                        // task to be scheduled
                        override fun run() {
                            handler.post(update)
                        }
                    }, DELAY_MS, PERIOD_MS)
                }
                hideLoading()
            } catch (e: ApiException) {
                hideLoading()
                e.message?.let { (this@MainActivity).showToast(it) }
            } catch (e: NoInternetException) {
                hideLoading()
                e.message?.let { (this@MainActivity).showToast(it) }
            }
        }
    }

    var mProgressDialog: DialogProgress? = null

    private fun showLoading() {
        try {
            hideLoading()
            mProgressDialog =
                DialogProgress(this, R.style.progress_dialog_text_style)
            mProgressDialog?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun hideLoading() {
        try {
            if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                mProgressDialog?.dismiss()
            }
        } catch (e: Exception) {
        }
    }


}