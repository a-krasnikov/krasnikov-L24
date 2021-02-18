package krasnikov.project.postsapp.feed.post.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.databinding.FragmentFeedPostsBinding
import krasnikov.project.postsapp.feed.post.ui.create.CreatePostFragment
import krasnikov.project.postsapp.feed.post.ui.feed.adapter.PostAdapter
import krasnikov.project.postsapp.utils.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : Fragment() {

    private lateinit var binding: FragmentFeedPostsBinding

    private val postsViewModel by viewModel<PostsViewModel>()

    private val adapter by lazy { PostAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        observePosts()
        observeStatusLoadedFromRemote()
        setupBtnListeners()
    }

    private fun setupRecycler() {
        val rvPosts = binding.rvPosts
        rvPosts.adapter = adapter
    }

    private fun setupBtnListeners() {
        binding.fabAdd.setOnClickListener {
            navigateToCreateFragment()
        }
    }

    private fun observePosts() {
        postsViewModel.posts.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    adapter.submitList(it.data)
                }
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        R.string.toast_error_loading,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun observeStatusLoadedFromRemote() {
        postsViewModel.errorLoadFromRemote.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), R.string.toast_error_refreshing, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun navigateToCreateFragment() {
        parentFragmentManager.commit {
            replace<CreatePostFragment>(R.id.fragment_container)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostsFragment()
    }
}