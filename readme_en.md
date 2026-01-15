# AiMsgBot - AI Message Guard Bot

## Product Introduction

**AiMsgBot** is an unattended message-sending robot program supporting multiple chat platforms, including:

- WeChat / WeChat Work

- QQ / QQ Business

- Feishu

- DingTalk

- And more...

With this tool, you can convert your chat account into an automated robot, which can execute tasks such as message sending, file transfer, and image sharing through API-driven commands, enabling business process automation.

## Technical Features

- **Legal & Compliant**: Implemented without reverse engineering, non-intrusive, no hook functions, no memory modification

- **High Stability**: Supports long-term operation without manual intervention

- **Data Security**: Provides independent data isolation; customized advanced security solutions available for sensitive scenarios

- **Multi-Platform Support**: Unified API interface compatible with different chat software

- **Easy to Use**: One-click deployment, runs in the system tray background

## Usage Steps

### 1. Preparation

- Prepare a **dedicated Windows computer** or virtual machine (performance sufficient to run the target chat software)

- Ensure the computer has **stable internet connectivity**

- Prepare a chat account that has **not been banned/muted by the official platform** (extremely important!)

### 2. Deployment Process

**Step 1: Download & Install**

- Visit the download link: [AiMsgBot Official Download](http://aibot.fx-i.cn/aibot.zip)

- Download and extract the main program and corresponding platform configuration files

**Step 2: Environment Setup**

- Install and log in to the target chat software on the computer

- Open any chat window (Note for DingTalk users: Due to interface differences between group chats and one-on-one chats, a single robot only supports one message-sending method)

**Step 3: Start the Service**

- Run the AiMsgBot program (you can find the icon in the system tray)

- The program will automatically run in the background and start listening for API commands

### 3. API Usage Guide

Obtain the machine code: Click the "About" button in the program interface and copy the displayed machine code (mcode)

**API Basic Information**

- Request URL: `http://msg.fx-i.cn:85/aibot/msg/?act=sendmsg`

- Request Method: POST

- Request Header: `Content-Type: application/json`

**Message Sending Example**

```JSON
{
  "mcode": "YOUR_MACHINE_CODE",  // Machine code
  "app": "dingtalk",            // Application type (dingtalk/wechat/qq/feishu)
  "data": [
    // Send text message
    {
      "c": "Group Name 1",
      "m": "Test Message 01",
      "t": "text",  // Message type: text/file/img
      "g":1 //1 group;0 person
    },
    
    // Send file (URL format)
    {
      "c": "contact",
      "m": "http://msg.fx-i.cn:85/aibot.txt",
      "t": "file",
      "g":0
    },
    
    // Send multiple messages to the same recipient
    {
      "c": "Group Name 3",
      "d": [
        { "m": "Test Message 1", "t": "text" },
        { "m": "http://example.com/image.png", "t": "img" }
      ],
      "g":1
    }
  ]
}
```

**Response Example**

```JSON
{
  "code": 1,
  "msg": "Robot is offline, messages have been temporarily stored in the queue",
  "data": {
    "robot_online": 0,
    "queue_msg_count": 3,
    "stats": {
      "total_in": "234",          // Total received messages
      "total_out": "225",         // Total sent messages
      "create_time": "2025-12-02 15:55:45",
      "update_time": "2025-12-05 09:11:54"
    }
  }
}
```

## Notes & Limitations

1. **Account Security**:

    - Strictly prohibit using accounts that have been penalized (banned/muted) by the official platform

    - It is recommended to use a dedicated work account to avoid affecting personal accounts

2. **Function Limitations**:

    - Note for DingTalk users: A single robot only supports either group chat or one-on-one chat message-sending (due to interface differences)

    - File transfer supports common formats; size limits vary by platform (generally no more than 20MB)

    - Some platforms may have restrictions on message frequency; avoid high-frequency sending to prevent account abnormalities

3. **Compliance Statement**:

    - This tool **can only be used for legitimate business scenarios** such as automatic notifications, business reminders, data reports, etc.

    - Strictly prohibit using it for illegal activities such as spam sending, fraud, privacy infringement, etc.

    - Any violation of usage terms will result in immediate account suspension and relevant liability investigation

## Summary

AiMsgBot provides enterprises with an efficient message automation solution. Through simple deployment and API integration, it realizes business process automation on chat platforms, saving labor costs and improving work efficiency.

Get started now:

1. Prepare a dedicated device

2. Download and install the program

3. Obtain the machine code

4. Integrate the API and start your automation journey

*Note: For customized security solutions or additional features, please contact official support.*
