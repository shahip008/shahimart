How do you deploy a database using AWS RDS?
 
 Step 1 : 
AWS RDS – Short Notes
Login → AWS Console → RDS
Click Create Database
Choose Engine → MySQL / PostgreSQL
Set:
DB name
Username & Password
Select Free tier (db.t3.micro)
Enable:
Public Access = YES ⚠️
Create DB

 Step 2: 
🔑 After Creation
Copy Endpoint + Port

Step 3 : 
🔒 Security Group (IMPORTANT)
Add Inbound Rule:
Port: 3306 (MySQL) / 5432 (Postgres)
Source: My IP / 0.0.0.0/0

Step 4 : 
🔗 Connect
jdbc:mysql://shahimart-db.ctku2ck0c8p8.ap-south-1.rds.amazonaws.com



Deploy Spring Boot on AWS (Elastic Beanstalk)
✅ 1. Build JAR File
In pom.xml → packaging = jar
Run:
mvn clean install -Dmaven.test.skip=true
JAR created in:
target/project-name.jar
✅ 2. Go to AWS
Open AWS Console
Search → Elastic Beanstalk
Click Create Application
✅ 3. Basic Details
Application Name → shahimart
Environment Name → Shahimart-env
Domain → shahimart (optional)
✅ 4. Platform
Platform → Java
Java Version → 17
✅ 5. Upload Code
Select → Upload your code
Choose → Local file
Upload → JAR file from target
Version → v1
✅ 6. Environment Setup
Environment Type → Web server environment
Instance → Single instance (for learning)
✅ 7. Roles (IMPORTANT)
Service Role → create
shahimart-aws-elasticbeanstalk-service-role
EC2 Role → create
shahimart-aws-elasticbeanstalk-ec2-role
✅ 8. Capacity
Auto Scaling:
Min → 1
Max → 4
(Real app → depends on traffic)
✅ 9. Advanced Settings
Tick → Combine purchase options
Add Environment Variables:
DB URL
Username
Password
✅ 10. Deploy
Click → Create Environment
Wait (5–10 mins)
✅ 11. Access App
After deployment → get URL like:
http://shahimart-env.ap-south-1.elasticbeanstalk.com
⚠️ Common Issues
App not running → check logs
DB not connecting → check security group
Port issue → use server.port=5000 (Beanstalk default)

🚀 One Line Revision
👉 Build JAR → Upload to Beanstalk → Configure → Deploy → Access URL


Deploy Frontend on Amplify (Without Git)
✅ 1. Open Amplify
Go to AWS Console
Search → Amplify
✅ 2. Start Deployment
Click New App
Select:
👉 Deploy without Git
✅ 3. Upload Project
Enter:
App Name
Environment (Branch) → production / main
Upload:
ZIP file (your frontend build)
✅ 4. Important (Before Upload)

👉 Make sure you upload build folder, not full project

For example:

React → upload build/ or dist/ folder
Angular → upload dist/
✅ 5. Deploy
Click Save & Deploy
Wait for deployment
✅ 6. Access App
Amplify will give:
👉 Live URL
⚠️ Common Mistakes

❌ Upload full source code
✔ Upload only build folder (compiled code)

❌ Missing index.html
✔ Ensure it is inside ZIP

🚀 One Line Revision

👉 Amplify → Deploy without Git → Upload build ZIP → Deploy




AWS CloudFront – Short Notes
✅ 1. Go to AWS
Open AWS Console
Search → CloudFront
Click → Create Distribution
✅ 2. Basic Setup
Distribution Name → shahimart
Type → Single website or app
✅ 3. Origin Setup (IMPORTANT ⚠️)
Origin Type → Custom Origin
👉 (Because Elastic Beanstalk not in list)
Origin Domain:
shahimart.ap-south-1.elasticbeanstalk.com
✅ 4. Default Settings
Protocol → HTTP (or HTTPS if configured)
Cache Policy → Default
✅ 5. Create Distribution
Click → Create
Wait (5–10 minutes)
✅ 6. Access Application
CloudFront will give URL:
https://dxxxxx.cloudfront.net

👉 Use this instead of Beanstalk URL

⚠️ Important Points
CloudFront = CDN (fast delivery)
Works as proxy between user & backend
Improves speed & security
🚀 One Line Revision

👉 Create Distribution → Add Beanstalk as Custom Origin → Deploy → Use CloudFront URL



SHAHIMART_CROSS_ORIGIN : https://production.dciirhahs77hb.amplifyapp.com