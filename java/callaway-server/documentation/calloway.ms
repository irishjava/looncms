\"turn off hyphenation 
.nr HY 0
\"Stuff related to font size and relative growing of size
.nr PS 9
.nr GROWPS 1.4
.nr PSINCR 1.1p
\"Headers
.ds LH Callaway Diablo Project
.ds CH 
.ds RH Page % 
\"Document follows
.TL 
Callaway Server Requirements
.AU 
Bryan Hunt
.AI 
Amp Communications 
.AB
Server functionality and general use cases for the Callaway golf game. 
This document defines the interaction between the User,Flash Application, Service and Server.
.AE 
.NH
Use Cases
.SH 
End User
.IP \(bu
Record individual user scores 
.IP \(bu
Create new user subscriptions
.IP \(bu
Subscribe friends via email 
.IP \(bu
Enter the draw
.IP \(bu
Obtain aggregate score data
.IP \(bu
Submit score data
.SH 
Reporting
.R
.IP \(bu
Weekly User statistics email 
.IP \(bu
Weekly User referals email
.sp 1
.KS
.NH
Domain Relationships
.PS 6
scale = .8;
User: [
down;
box width 2 "\fBUser\fP";
Pk: box width 2 "pk:String" "\fI(Primary key)\fP";
box width 2 "firstName:String" "\fI(First Name)\fP";
box width 2 "lastName:String" "\fI(Last Name)\fP";
box width 2 "email:String" "\fI(Email Address)\fP";
box width 2 "country:String" "\fI(Country)\fP";
box width 2 "city:String" "\fI(City)\fP";
box width 2 "subscriber:boolean" "\fI(Opted in)\fP";
box width 2 "dob:String" "\fI(Date of Birth)\fP";
box width 2 "creationDate:Date" "\fI(Account Creation Date)\fP";
box width 2 "remoteIp:String" "\fI(Recorded IP Address )\fP";
box width 2 "version:Integer" "\fI(Used for locking)\fP";
];

move left 1.4 down 0 from User.nw ;
Score: [
up; 
down ;
box width 2 "\fBScoreboard\fP";
box width 2 "pk:String" "\fI(Primary key)\fP";
box width 2 "score:long" "\fI(Score)\fP";
box width 2 "creationDate:Date" "\fI(Generation time)\fP";
box width 2 "level:Integer" "\fI(Level, upon which score created)\fP";
box width 2 "remoteIp:String" "\fI(Recorded IP Address)\fP";
box width 2 "version:Integer" "\fI(Used for locking)\fP";
UserFk : box width 2 "user:User" "\fI(User\fP";
];
arrow from Score.UserFk.e to User.Pk.w;

move right 1.4 down 0 from User.ne;
Recommendation: [
down;
box width 2 "\fBRecommendation\fP";
box width 2 "pk:long" "\fI(Primary key)\fP";
box width 2 height .7 "recipientEmail:String" "\fI(Recipient email)\fP";
box width 2 height .7 "senderEmail:String" "\fI(Sender email)\fP";
box width 2 "creationDate:Date" "\fI(Generation time)\fP";
box width 2 height .7 "optIn:Boolean" "\fI(Sender Opted in" "to receive spam)\fP";
#UserFk : box width 2 "userFk:User" "\fI(User primary key)\fP";
.n at last [].n;
] ;
#arrow from Recommendation.UserFk.w to User.Pk.e;
#box at Scoreboard.sw - (-0.5,3);


.PE
.KE
.KS
.NH
Service Methods
.LP
All service calls except the initialise method 
(for obvious reasons) 
will require the existance of an accredited session upon the server. 
.LP
The cookie will be set with a key generated partially by the client and transmitted upon initialisation of the flash application.
.R
.
.
.
.TS 
tab(|),allbox;
cbcb
ll.
Method|Description
\fInonce() : String\fP|T{
.na
Returns the \fInonce\fP value, this value could (and should) change frequently. It remains to be determined how we will force the client to re-initialize upon a value change.
T}
T{
.na
\fIinitialise( Credentials ) : InitializationResult\fP
T}|T{
.na
Send the "secret" key from the Flash application to the server
T}
T{
.na
\fIinvite( Invite ) : InvitateResult\fP
T}|Invite a friend
\fIlogin( Login ) : LoginResult\fP|Login to application 
T{
.na
\fIsubmit( ScoreSubmission ) : ScoreSubmissionResult\fP
T}|Upon completing a level, the Users scores are transmitted to the server.
T{
.na
\fIsubscribe( SubscriptionRequest) : SubscriptionResult\fP
T}|Create a game account
\fIlistMyScores() : [] Score\fP|Return a sorted array of Score objects.
\fIlistTopScores() : ScoreBoard\fP|List the top 25 scores.
.TE
.KE
.KS
.NH
DTO Objects 
.R
.LP
These are the Objects that are transmitted between the client and the service.
.TS
tab(;)allbox,expand;
cb|lb|lb
ci|l|lm\fI.
Class;Attribute;Description
Login;email:String;User email address
LoginResult;success:boolean;
\^;maxLevel:int;T{
.na
The maximum level the user has attained in the game.
T}
\^;reason:String;The reason (if any) for failure.
UserDetails;email:String;
Credentials;token:String;T{
.na
A Hexidecimal encoded, digest; calculated the as the MD5 digest of the concatenated Nonce and client secret.
T}
\^;date;Current date and time 
InitializationResult;success:boolean;
\^;reason;The reason (if any) for failure.
ScoreSubmission;level:int;
\^;value:long
ScoreSubmissionResult;success:boolean;
\^;reason;The reason (if any) for failure.
ScoreBoard;date:
\^;rows:ScoreRow[]
ScoreRow;name;The user name.
\^;score;T{
.na
The sum of level scores which this user has attained.
T}
\^;date;The date upon which this high score was attained.
\^;location;T{
.na
This could be country or state i.e France, Texas, Alberta.
T}
\^;sublocation;City, i.e Paris, Austin or Calgary.
Invite;senderEmail;String
\^;senderName;String
\^;recipients;String[]
\^;message;String
\^;optIn;boolean
InviteResult;success:boolean
\^;reason;The reason (if any) for failure.
MaxScores;scores[String,Number];Map of Usernames and scores
SubscriptionRequest;email;
\^;firstName;first name
\^;lastName;
\^;location;\fISee ScoreRow above\fP 
\^;subLocation;\fISee ScoreRow above\fP 
\^;optIn;\fIHave they subscribed to email messages ?\fP 
\^;dob;T{
.na
Users Date Of birth. It is formatted with the pattern: ""DD/MM/YYYY"
T}
SubscriptionResult;success;boolean
\^;reason;The reason (if any) for failure.
.TE
.KE
.KS
.NH
Use Cases
.R
.NH 2
Application Initialisation
.R
.PS 5.5 
scale = .8;
right;
U:[box height 0.2 color "antiquewhite" outline "black" "User"]
move;
F:[box same color "antiquewhite" outline "black" "Flash";]
move;
S:[box same color "antiquewhite" outline "black" "Service";]
move;
SS:[box same color "antiquewhite" outline "black" "Session";]

User:line dashed down 1 from U.s;
Flash:line dashed down 1 from F.s;
Service:line dashed down 1 from S.s;
Server:line dashed down 1 from SS.s;

arrow "start (1)" "" from User.n - (0,0.1) to Flash.n - (0,0.1); 
line from Flash.n - (0,0.2) left 0.3 "initialise (2)" "" then down 0.05 then right 0.3 ->;
arrow "initialise(..args)(3)" "" from Flash.n - (0,0.3) to Service.n - (0,0.3); 
arrow "create server state(4)" "" from Service.n - (0,0.4) to Server.n - (0,0.4); 
arrow "return DailyToken(5)" "" from Service.n - (0,0.5) to Flash.n - (0,0.5); 
arrow "send (6)" "" from Flash.n - (0,0.6) to Service.n - (0,0.6); 
arrow "store verifcation authenticated" "" from Service.n - (0,0.6) to Server.n - (0,0.6); 
.PE
.IP 1
Flash initialises.
.IP 2
Flash requests daily nonce.
.IP 3
Flash concatenates secret client key and nonce, md5's and transmits to server.
.IP 4
Service checks that this matches it's records.
.IP 5
Service creates appropriate Server State.
.SH 3
Footnote
.R
.LP 
Subsequent requests will pass through an Security Aspect 
which allows or deny access to individual remote methods by 
checking the verification state in Server memory.
.sp
.I
If Verification fails, the game will fail to execute.
.KE
.KS
.NH 2
User Interface Initialises
.LP 
The Flash Applicataion presents the User with three choices:
.IP 1
User choses to play a sample level, goto: \fIPlay a Sample Level\fP
.IP 2
User choses to log into the application, goto: \fILog onto application\fP
.IP 3
User choses to invite a friend, goto: \fIInvite a Friend\fP
.KE
.KS
.NH 2
Play a Sample Level
.R
.LP
The User is allowed to play the first level without having submitted to the registration process.
.IP 1
The player plays the Sample Level.
.IP 2
If the User completes the Sample Level, goto \fIContinue past Level 1\fP
.KE
.KS
.NH 2
Continue past Level 1
.R
.LP
Before the User can save his Score and play higher Levels, he must provide his personal details. 
The User makes a choice:
.IP \[bu] 
User provides his Personal Details, goto \fIUser provides Personal Details.
.IP \[bu]
User declines to enter his Personal Details, go no further.
.R
.KE
.KS
.NH 2
User provides Personal Details
.R
.B1
.DS L
.PS 5.5 
scale = .8;
right;
#U:[box rad 0.06 height 0.2 fill 0.1 "user";]
U:[box height 0.2 color "antiquewhite" outline "black" "User";]
move;
F:[box same color "antiquewhite" outline "black" "Flash";]
move;
S:[box same color "antiquewhite" outline "black" "Service";]
move;
SS:[box same color "antiquewhite" outline "black" "Session";]

User:line down 1 from U.s;
Flash:line down 1 from F.s;
Service:line down 1 from S.s;
Server:line down 1 from SS.s;

arrow "enters personal details" "" from User.n - (0,0.1) to Flash.n - (0,0.1); 
line from Flash.n - (0,0.2) left 0.3 "validate" "" then down 0.05 then right 0.3 ->;
arrow "subscribeUser(..args)" "" from Flash.n - (0,0.3) to Service.n - (0,0.3); 
arrow "return UserLoginResult" "" from Service.n - (0,0.5) to Flash.n - (0,0.5); 
arrow "indicate continue" "" from Flash.n - (0,0.6) to User.n - (0,0.6); 
.PE
.DE
.B2
.LP
The User is presented with a form, into which he enters his Personal Details. 
.TS 
tab(|),allbox;
cs
ll.
User Details
User
First Name
Last Name
Email Address|\fIPersonal identifier\fP
Country|location \fISee ScoreRow DTO\fP
City|subLocation \fISee ScoreRow DTO\fP
Date of Birth
.TE
.nr step 1 1
.IP \n[step]
The Personal Details undergo client side validation.
.IP \n+[step]
The Personal Details are transmitted to the Server.
.RS
.IP \n[step].1
The Server handles the Subscription Request.
.RS
.IP \n[step].1.1
If the User Details have not already been sent, they are stored upon the Server. 
The Server replies with a User Login token indicating success. No email is sent to the User 
.IP \n[step].1.2
If they have been previously Stored upon the Server. 
The Server replies with a User Login token containing:
The details should be updated if they are different. This removes the need for a profile page for the user to update their details.
.RE
.RE
.IP 3
The Flash Application allows the User to progress to the next level.
.KE
.KS
.NH 2
User Authentication Process
.R
.PS 6
#scale = .8;
right;
#U:[box rad 0.06 height 0.2 fill 0.1 "user";]
U:[box height 0.2 color "antiquewhite" outline "black" "User";]
move;
F:[box same color "antiquewhite" outline "black" "Flash";]
move;
S:[box same color "antiquewhite" outline "black" "Service";]
move;
SS:[box same color "antiquewhite" outline "black" "Server";]
User:line dashed down .7 from U.s;
Flash:line dashed down .7 from F.s;
Service:line dashed down .7 from S.s;
Server:line dashed down .7 from SS.s;
arrow "enter email(1)" "" from User.n - (0,0.1) to Flash.n - (0,0.1);  
arrow "login(Login):LoginResponse(2)" "" from Flash.n - (0,0.2) to Service.n - (0,0.2);  
#line "check validity(3)" "" from Service.n -(0,0.6) left then down -(0,0.1) then right (0,0.1);
arrow "success" "" from Flash.n - (0,0.4) to User.n - (0,0.4); 
arrow "failure" "" from Flash.n - (0,0.4) to User.n - (0,0.6); 
.PE
.SH 3
Prerequisites 
.LP
Client has already undergone Verification Process.
.R
.IP \fB1 3 
User enters Email Address into Flash
.IP \fB2 3 
Flash transmits Email Address to Service
login(Login):LoginResponse
.IP \fB3 3 
Service checks that is a valid Email Address
.IP \fB4 3 
Service stores Authentication State upon Server
.IP \fB5 3 
Service transmits response to client
return LoginResponse
.IP \fB6 3 
Flash reads the LoginResponse
.SH 3
Finally
.LP
If the response contains a code of LOGIN_SUCCESS, then he can access restricted levels up to the level Authorised by the response maxLevel field.
.sp
In version 1 of this application the User will not have an interface by which they may select their level - this functionality may be added in future upgrades.
.LP
If the response contains a code of LOGIN_FAILURE, then the user is presented with the error message contained in the response message field
.LP
User fills out a form in the Flash Application. The form requires them to enter their Email Address.
.IP 1
Form contents are added to a Login object and transmitted to the server.
.IP 2
The Server tries to retrieve the matching User Details from the Database.
.RS
.IP \[bu] 
If The Server is able to retrieve the User Details.
The server replies with a User Login token containing:
.LP
.RS
LOGIN_SUCCESS
.LP
The User is allowed to continue, either to \fISkip Registration\fP or \fIContinue Play\fP.
.RE
.IP \[bu]
If The Server is NOT able to retrieve the User Details.
The server replies with a User Login token containing:
.RE
.LP
.RS
LOGIN_FAILURE
.LP
The User must enter his Email Address again or return to the start menu.
.RE
.KE
.KS
.R
.NH 2
Invite a Friend
.R
.LP
Users do not need to be registered in order to refer a friend.
.LP
.DS L
We need to record the email address of the person running the use case. 
Then we need to record how many people that person sent emails to. 
This can be competely seperated from the mail Users table.
.DE
.TS 
tab(:),allbox,expand;
cbs
lil.
Invitation Form
senderEmail:Senders email address
friendsAddresses:Friends Email addresses
senderName:Sender name
message:Message to the recipient
optIn:Sender consented to email marketing
.TE  
.KE
.IP \[bu]
If the users chooses not to log into the application: 
.RS
.IP 1.1
The Flash Application downloads level 1 from the server.
.IP 1.2  
The User plays level 1. 
.RE
.IP \[bu]
If the user chooses to log into the application: 
.RS
Goto \fIUser login Use Case\fP
.RE   
.KS
.NH 2
User login
.sp
.R
Flash application asks User for his email address.
Flash application sends his email address to the server.
The server tries to retrieve the User's details using the Email Address as Primary Key.
If the details exist:
  The Server returns the Users top scores to the Flash Client. 
  The Server adds the account details to the Server Side Session.
If the details do not exist:
  It increments a login failure counter for that User's Session.
  If the login failure counter is greater than 5 it prevents subsequent user logins(TBD)
  It returns a failure:
    Which is handled by the application: 
      Which prompts the user to create an account 
.KE
.KS
.NH 2
Use Cases Hierarchy
.PS 6 6
scale = .8;
down;
TopScores:ellipse  width 2 height 1.1 "View Top Scores" color "antiquewhite" outline "black";
down;
move;
Init : ellipse  same  "Application" "Initialisation" color "antiquewhite" outline "black";
move;
Play: ellipse same "Play a level" color "antiquewhite" outline "black";
move to Play.e;
move right;
Register: ellipse same "Register" color "antiquewhite" outline "black";
move to Register.s;
move down 
Login: ellipse same "Login" color "antiquewhite" outline "black";
move to Play.s;
down;
move;
PlayRestricted: ellipse same "Play" "Restricted Levels" color "antiquewhite" outline "black";
move;
EnterDraw: ellipse same "Enter Draw" color "antiquewhite" outline "black";
move to Init.e;
move right ;
Invite: ellipse same  "Invite a Friend" color "antiquewhite" outline "black";
move to Init.w;
move to EnterDraw.w;
move down 2;
WeeklyReportEmails:ellipse same  "Weekly Report Emails" color "antiquewhite" outline "black";
WeeklyReportStatistics:ellipse same  "Weekly Report Statistics" color "antiquewhite" outline "black";
arrow dotted from Init.s to Play.n;
arrow dotted from Init.n to TopScores.s;
arrow dotted from Init.e to Invite.w;
arrow dotted from Play.s to PlayRestricted.n;
arrow dotted from Play.e to Register.w;
arrow dotted from PlayRestricted.s to EnterDraw.n;
arrow dotted from PlayRestricted.e to Login.w;
arrow dotted right from Invite.e then down 3 then to Login.e;
.PE
.KE
.KS
.NH 2
State Diagram
.PS
down;
move;
circle "Init";
move left down;
down;
box "Play a level";
move;
box "Sign up";
move;
arrow from 1st circle.s to 2nd last box.n;
arrow from 2nd last box.s to last box.n;
move;
move to 1st circle.s;
move right down;
down;
box "Login";
move;
box "Play higher" "levels"
move;
box "Enter the" "draw"
arrow from 1st circle.s to 3nd last box.n;
arrow from 3rd last box.s to 2nd last box.n;
arrow from 2nd last box.s to last box.n;
#now interdependencies
arrow from 1st box.e to 3rd box.w;
arrow from 2nd box.e to 4th box.w;
move to last box.s;
move down;
circle "finish"
arrow from last box.s to last circle.n;
move to 1st circle.e;
move right;
box 2 "Invite" " a friend";
arrow from 1st circle.e to last box.w;
move right right;
circle "finish"
arrow from last box.e to last circle.w;
move to 1st box.w;
move left;
circle "finish";
arrow from 1st box.w to last circle.e;
.PE
.KE
