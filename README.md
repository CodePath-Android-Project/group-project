# group-project
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
- [x] User authentication
    - [x] User can sign up to create a new account using Parse authentication.
    - [x] User can log in and log out of his/her account.
    - [x] The current signed in user is persisted across app restarts.
- [x] User can navigate between screens using the bottom navigation bar.
- [ ] UI
   - [x] App icon is personalized 
   - [x] App color theme
- [ ] User can  post or share a recommended job.
   - [x] User can compose a post this to the app
   - [x] Newly created posts are inserted into the timeline and do not rely on a full refresh
- [ ] Profile pages for each user
   - [x] User profile details.
   - [x] User can see all their posted posts.
   - [ ] User can see all their saved posts.
- [ ] Job posting feed
- [ ] Job posting feed for international students from companies that sponsor.
- [ ] User can see a post details.

### Video Walkthrough 
Here's a walkthrough of implemented user stories:

<img src="user-authentication.gif" alt="authentication" width=250> <img src="user-is-persisted.gif" alt="user-is-persisted" width=250> <img src="sprint3.gif" alt="sprint3-submission" width=250>

Gif created with [Kap](https://getkap.co).

**Optional Nice-to-have Stories**

- [ ] Users can save jobs they are interested in
- [ ] Skills companies are looking for
- [ ] Where to look for such skills
- [ ] Users can see populate or trending jobs and jobs that match most their skills
- [ ] Social (messaging between users)
- [ ] Settings (Accessibility, Notification, General, etc.)

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
    * User can follow companies that are or could be hiring in the future.

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
<img src="https://imgur.com/J63TTlw.jpg" width=800 height=400 />
<img src="https://imgur.com/CN6ugk8.png" width=800 height=400 />

### [BONUS] Interactive Prototype
<img src="https://imgur.com/aYYrc7l.gif" width=200 />
<img src="https://imgur.com/XXHXuQH.gif" width=200 />


## Schema

### Models

Job(Post)

| Property         | Type        | Description |
| --------         | --------    | ----------- |
| objectId         | String      | unique id for the user post (default field)|
| author           | User        | User profile-pic|
| createdAt        | DateTime    | date when post is created (default field)|
| updatedAt        | DateTime    | date when post is last updated (default field)|
| isFull           | boolean     | see if job is full.
| skills           | List<Skill> | list of top skills needed for this job
| offerSponsorship | boolean     | inform user if their visa qualify for this position|
| applicants       | Number      | number of applicants for this position|
| hashtags          | Symbol      | get information about a specific job, industry, major or qualification needed, ...|
| likesCount       | Number      | number of likes for the job |


User

| Property    | Type     | Description |
| --------    | -------- | ----------- |
| objectId    | String   | unique id for current user (default field)|
| userName    | String   | user name set by user on account creation |
| password    | String   | password set by user on account creation |
| email       | String   | email provided by user on account creation |
| createdAt   | DateTime | date when account is created (default field) |
| updatedAt   | DateTime | date when account is last updated (default field)|

### Networking

#### List of network requests by screen

* Trending Jobs
    * Read/GET (query all available jobs post and all posts where user is author)
      ```
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
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
        });
        ```
    
    * Create/POST (create a new like on a post)
    * Delete/DELETE (delete existing like)

* Search Jobs
    * Read/GET (query all available job post that match search)

* Search Results
    * Create/POST(post all available job posts that match search)
    * Read/Get(get post information)

* Create Jobs Screen
    * Create/Post(post job to the database)
        ```
        ParseObject post = new ParseObject("Post");
        post.put("title", "My New Post");
        post.put("body", "This is some great content.");
        post.put("user", user);
        post.saveInBackground();
        ```

* Post Details Screen
    * Read/Get(get post information)
    
* Profile Screen
    * Read/GET(query logged in user object)
    * Update/Put(update user information: profile image, resume)
        
* User Sign Up Screen
   * Read/GET
    ```
    ParseUser user = new ParseUser();
    user.setUsername("my name");
    user.setPassword("my pass");
    user.setEmail("email@example.com");
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
   ```
* User Login Screen
   * Read/GET
    ```
    ParseUser.logInInBackground("Jerry", "showmethemoney", new LogInCallback() {
      public void done(ParseUser user, ParseException e) {
        if (user != null) {
          // Hooray! The user is logged in.
        } else {
          // Signup failed. Check the ParseException to see what happened.
        }
      }
    });
    ```

### [OPTIONAL:] Existing API Endpoints

Indeed Job Search API
   * Base URL - https://opensource.indeedeng.io/api-documentation/docs/job-search/#requirements
   
   | HTTP Verb | Endpoint | Description |
   | --------  | -------- | ----------- |
   | GET | /query | query all available jobs with the provided parameters |
   | GET | /location | location where the job is available (postal code or combination of city, and state/province/region) |
   | GET | /jt | job's type (fulltime, parttime, contract, internship, or temporary |
   | GET | /radius | distance from job search location |
   | GET | /fromage | number of days since job was posted |
   | GET | /co | country to search for |
   | GET | /results | results of search based on provided parameters |
   | GET | /results/jobtitle | title of job|
   | GET | /results/company  | company offering the job |
   | GET | /results/city  | city where job is offered |
   | GET | /results/state  | state where job is offered |
   | GET | /results/country  | country where job is offered |
   | GET | /results/formattedLocation  | city and state where job is offered |
   | GET | /results/date  | date from which job is available |
   | GET | /results/snippet  | required skills needed for the job |
   | GET | /results/sponsored  | whether sponsorship is offered for this job or not |
   
   
   Icon generated from [Icons8](href="https://icons8.com/icons/set/find-matching-job">).
   
