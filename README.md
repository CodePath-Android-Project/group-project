# group-project
Unit 8: Group Milestone - README Example
# Job and Skill Finder

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
Helps students whether they're local or international to look for jobs based on the skills they have to offer and to look for skills that are desired by employers.

### App Evaluation
- **Category:** Jobs database for students
- **Mobile:** Mobile is essential for instant logging and real-time experience, uses maps too. Mobile first experience
- **Story:** Allow users to find job based on the skills they have to offer,and student work status authorization.
- **Market:** All students, whether they are international or local, seeking job can enjoy this app.
- **Habit:** Users are using this frequently in their job haunt. They will be notified about jobs availabilty from compamies they are interested in, and positions they’re interested in based on their location.
- **Scope:** This app idea came in as many International struggle to find job at companies, because not all of them offer sponsorship to not US citizens. This app will then help them to know which company they can work at based on their skills they have to offer. It also lets students to know what skills employers are looking for to get hired. Its helps students whether they are local or international students.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**
* User authentification
* Job posting feed with filter
* Job posting feed for international students from companies that sponsor
* Profile pages for each user where they can see all their saved posts
* Settings (Accesibility, Notification, General, etc.)

**Optional Nice-to-have Stories**

* Users can save jobs they are interested in
* Skills companies are looking for
* where to look for such skills
* Users can tap a post to see more details about it
* Users can see populate or trending jobs and jobs that match most their skills
* User can search for jobs by a hashtag
* User can post or share a recommended job
* Social (messaging between users)

### 2. Screen Archetypes

* Login Screen for current users
    * User can login
* Signup or Registration for new users
   * User can create a new account
       * Username (required)
       * Password (required)
       * Email (required)
       * Phone (optional)
* Stream or Timeline for logged users
   * User can view jobs feed of trending jobs
   * User can double tap a post to save it
   * ...
* Creation
    * User can post or share a recommended job
    * ...
* Profile
    * User can see all their saved posts
* Search
    * User can search for job at companies that offer sponsorship.
    * User can search for jobs based on desired filter.
    * User can follow comanies that are or could be hiring in the future.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home feed
* Post details
* Search Jobs
* Post job.
* Profile

Optional:
* ...

**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available
* Login Screen -> Home
* Signup or Registration Screen -> Home
* Stream or Timeline Screen -> None, but in future will go to post details screen
* Creation -> Home
* Search -> none for now
* Profile -> Text field to be modified. 
* Settings -> Toggle settings

## Wireframes
<img src="https://imgur.com/4Ain4Cw.jpg" width=800><br>

### [BONUS] Digital Wireframes & Mockups
<img src="https://imgur.com/J63TTlw.jpg" width=800 height=400>

### [BONUS] Interactive Prototype
<img src="https://imgur.com/aYYrc7l.gif" width=200>


## Schema

### Models

Job

| Property         | Type     | Description |
| --------         | -------- | ----------- |
| objectId         | String   | unique id for the user post (default field)|
| author           | User     | User profile-pic|
| createdAt        | DateTime | date when post is created (default field)|
| updatedAt        | DateTime | date when post is last updated (default field)|
| isFull           | boolean  | see if job is full.
| skills           | List<Skill>| list of top skills needed for this job
| offerSponsorship | boolean  | inform user if their visa qualify for this position|
| applicants | Number | number of applicans for this position|
| hastags | Symbol | get information about a specific job, industry, major or qualification needed, ...|
| likesCount | Number | number of likes for the job |


### Networking

#### List of network requests by screen

* ### Trending Jobs
    * Read/GET (query all available jobs post and all posts where user is author)
    
        `ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.whereKey("author", equalTo: currentUser);
        query.order(byDescending: "createdAt");
        query.findObjectxInBackground(new FindCallBack<Post>() {
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for(Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + " - Username: " + post.getUser().getUsername());
                }

                //TODO: what to do with posts go here...
                allPosts.clear();
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false); // refresh is done
            }
        });`
    
    * Create/POST (create a new like on a post)
    * Delete/DELETE (delete existing like)

* ### Search Jobs
    * Read/GET (query all available job post that match search)

* ### Search Results
    * Create/POST(post all available job posts that match search)
    * Read/Get(get post information)

* ### Post Jobs Screen
    * Create/Post(post job to the database)

        ParseObject post = new ParseObject("Post");
        post.put("title", "My New Post");
        post.put("body", "This is some great content.");
        post.put("user", user);
        post.saveInBackground();

* ### Post Details Screen
    * Read/Get(get post information)
    
* ### Profile Screen
    * Read/GET(query logged in user object)
    * Update/Put(update user information: profile image, resume)
        
* ### User Sign Up Screen
   * Read/GET
   
    ParseUser user = new ParseUser();
    user.setUsername("my name");
    user.setPassword("my pass");
    user.setEmail("email@example.com");

    // other fields can be set just like with ParseObject
    user.put("phone", "650-253-0000");

    user.signUpInBackground(new SignUpCallback() {
      public void done(ParseException e) {
        if (e == null) {
          // Hooray! Let them use the app now.
        } else {
          // Sign up didn't succeed. Look at the ParseException
          // to figure out what went wrong
        }
      }
    });

* ### User Login Screen
   * Read/GET
   
    ParseUser.logInInBackground("Jerry", "showmethemoney", new LogInCallback() {
      public void done(ParseUser user, ParseException e) {
        if (user != null) {
          // Hooray! The user is logged in.
        } else {
          // Signup failed. Look at the ParseException to see what happened.
        }
      }
    });

### [OPTIONAL:] Existing API Endpoints
