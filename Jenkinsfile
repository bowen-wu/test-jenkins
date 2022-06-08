def dockerRegistryIp = "118.195.243.156";
def projectName = "test-jenkins";
def jenkinsSSHCredentialId = "8d56f0d5-ba10-437d-8655-6188d980fb81";
def responseJson = new URL("http://${dockerRegistryIp}:5000/v2/${projectName}/tags/list").getText(requestProperties: ['Content-Type': "application/json"]);


// responseJson: {name:xxx,tags:[tag1,tag2,...]}
Map response = new groovy.json.JsonSlurperClassic().parseText(responseJson) as Map;

def versionsStr = response.tags.join('\n');

pipeline {
    agent any
    stages {
        stage('Deploy') {
            input {
                message "Choose a version"
                ok "Deploy"
                parameters {
                    choice(choices: versionsStr, description: 'version', name: 'version')
                }
            }
            steps {
                echo "🎉 You choose version: ${version} 🎉"
                // ssh credential id
                sshagent (credentials: ["$jenkinsSSHCredentialId"]) {

                    sh "ssh -o StrictHostKeyChecking=no root@${dockerRegistryIp} 'docker pull ${dockerRegistryIp}:5000/${projectName}:${version}'"
                    echo "🎉 Pull ${dockerRegistryIp}:5000/${projectName}:${version} Success~ 🎉"
                    sh "ssh -o StrictHostKeyChecking=no root@${dockerRegistryIp} 'docker rm -f ${projectName} || true'"
                    sh "ssh -o StrictHostKeyChecking=no root@${dockerRegistryIp} 'docker run --name ${projectName} -p 8080:8080 -d ${dockerRegistryIp}:5000/${projectName}:${version}'"
                    echo "🎉 Restart Success~ 🎉"
                }
            }
        }
    }
}
