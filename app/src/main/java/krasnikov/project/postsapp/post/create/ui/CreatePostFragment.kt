package krasnikov.project.postsapp.post.create.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.app.App
import krasnikov.project.postsapp.databinding.FragmentCreatePostBinding
import krasnikov.project.postsapp.post.common.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.post.create.domain.validate.error.ValidationError
import krasnikov.project.postsapp.utils.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePostFragment : Fragment() {

    private lateinit var binding: FragmentCreatePostBinding

    private val createPostViewModel by viewModel<CreatePostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBtnListeners()
        setupUser()
    }

    private fun setupUser() {
        binding.tvUserId.text = getString(R.string.title_post_user_id, App.userId)
    }

    private fun setupBtnListeners() {
        binding.btnCreate.setOnClickListener {
            createPost()
        }

        binding.btnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun createPost() {
        when (val result = createPostViewModel.createPost(createPostModel())) {
            is Result.Success -> {
                showToast(R.string.text_post_created)
                parentFragmentManager.popBackStack()
            }
            is Result.Error -> {
                showError(result.exception)
            }
        }
    }

    private fun showError(exception: Exception) {
        when (exception) {
            is ValidationError -> {
                when (exception.stringRes) {
                    R.string.error_post_title_content_validation,
                    R.string.error_post_title_length_validation -> {
                        binding.etTitle.setError(getString(exception.stringRes))
                    }
                    R.string.error_post_body_length_validation -> {
                        binding.etBody.setError(getString(exception.stringRes))
                    }
                }
            }
        }
    }

    private fun showToast(@StringRes stringRes: Int) {
        Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
    }

    private fun createPostModel(): PostEntity {
        return PostEntity(
            userId = App.userId,
            title = binding.etTitle.text.toString(),
            body = binding.etBody.text.toString(),
            isLocal = true
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreatePostFragment()
    }
}