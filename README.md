# Blog Application Controller Classes

This repository contains the controller classes for a Blog Application implemented using Spring Boot. The application implements role-based authentication and provides various endpoints for managing users, posts, comments, and categories.

## Controller Classes

### AuthController
- Responsible for user authentication and registration.
- Endpoints:
  - `POST /api/auth/register`: Register a new user.
  - `POST /api/auth/login`: Log in an existing user.

### CategoryController
- Manages blog post categories.
- Endpoints:
  - `POST /api/category/createcategory`: Create a new category (Admin only).
  - `GET /api/category/findallcategory`: Get all categories (Admin only).
  - `GET /api/category/findbyname`: Find a category by name (Admin only).
  - `PUT /api/category/update`: Update a category (Admin only).
  - `DELETE /api/category/delete`: Delete a category (Admin only).

### CommentController
- Handles comments on blog posts.
- Endpoints:
  - `POST /api/comments/createcomment/{postId}`: Create a new comment.
  - `PUT /api/comments/editcomment`: Edit a comment.
  - `DELETE /api/comments/delete`: Delete a comment.

### PostController
- Manages blog posts.
- Endpoints:
  - `POST /api/posts/createpost`: Create a new post.
  - `PUT /api/posts/editpost`: Edit a post.
  - `GET /api/posts/feed`: Get all posts.
  - `GET /api/posts/search`: Search posts by title.
  - `GET /api/posts/findbycategory`: Find posts by category.
  - `DELETE /api/posts/delete`: Delete a post.

### UserController
- Manages user-related operations.
- Endpoints:
  - `PUT /api/user/update`: Update user details (Admin or User).
  - `GET /api/user/getAll`: Get all users (Admin only).
  - `GET /api/user/findbyemail`: Find a user by email (Admin only).
  - `DELETE /api/user/delete`: Delete a user .

## Role-Based Authentication
- **Admin**: Has access to all endpoints. please find the admin credentials bellow for testing.
- **email**:`kishor@gmail.com`
- **password**:`kishor123`
- **User**: Limited access, can perform certain actions like updating their own profile.
- Authentication is implemented using JSON Web Tokens (JWT).

## Entity-Relationship (ER) Diagram
![Screenshot (177)](https://github.com/kishork18/Alphaware_Blog_Application/assets/119414124/2e43e328-2fd6-4184-867e-42d1fc03e8ba)

## Deployment
The application is deployed and accessible at ![here](https://alphawareblogapplication-production-a674.up.railway.app/swagger-ui/index.html#/post-controller/getAllPostHandler).
