package krasnikov.project.postsapp.postsfeed.post.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.databinding.FragmentPostsBinding
import krasnikov.project.postsapp.postsfeed.post.ui.adapter.PostAdapter
import krasnikov.project.postsapp.postsfeed.post.ui.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding

    private val postsViewModel by viewModel<PostsViewModel>()

    private val adapter by lazy { PostAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsViewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        observePosts()
        observeLoadingStatus()
    }

    private fun setupRecycler() {
        val rvPosts = binding.rvPosts
        rvPosts.adapter = adapter
    }

    private fun observePosts() {
        postsViewModel.postsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun observeLoadingStatus() {
        postsViewModel.loadedSuccessLiveData.observe(viewLifecycleOwner) {
            if (!it)
                Toast.makeText(requireContext(), R.string.error_loading, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostsFragment()
    }
}