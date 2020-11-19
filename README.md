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




## Model: Trending Jobs

| Property         | Type     | Description |
| --------         | -------- | ----------- |
| objectId         | String   | unique id for the user post (default field)|
| author           | User     | User profile-pic|
| createdAt        | DateTime | date when post is created (default field)|
| updatedAt        | DateTime | date when post is last updated (default field)|
| isFull           | boolean  | see if job is full.
| topSkill1        | String   | top 1 skill needed for this job|
| topSkill2        | String   | top 2 skill needed for this job|
| topSkill3        | String   | top 3 skill needed for this job|
| offerSponsorship | boolean  | inform user if their visa qualify for this position|
| applicants | Number | number of applicans for this position|
| hastags | Symbol | get information about a specific job, industry, major or qualification needed, ...|
| likesCount | Number | number of likes for the job |

##Outline Parse Network Requests


### Trending Jobs
* Read/GET (query all available jobs post)
* Create/POST (create a new like on a post)
* Delete/DELETE (delete existing like on job post)
* ...

### Search Jobs
* Read/GET (query all available job post that match search)

### Search Results
* Create/POST(post all available job posts that match search)
* Read/Get(get post information)

### Post Jobs
* Create/Post(post job to the database)


### Profile
* Read/GET(get user information)
* Update/Put(update user information)
### Post Details
* Read/Get(get post information)


