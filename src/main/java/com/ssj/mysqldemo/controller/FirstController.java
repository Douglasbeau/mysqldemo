package com.ssj.mysqldemo.controller;

import com.ssj.mysqldemo.model.Student;
import com.ssj.mysqldemo.service.StudentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api("学生管理")
@Controller
@RequestMapping("/student")
@Slf4j
public class FirstController {
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    private final StudentService studentService;
    public FirstController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @SendTo("/topic")
    @MessageMapping({"/queue"})
    public String greeting(Message message, SimpMessageHeaderAccessor headerAccessor) {
        // Extract the session ID of the client that created the session
        String sessionId = headerAccessor.getSessionId();
        log.info("Controller收到来自 {} 的消息: {} ", sessionId, new String((byte[]) (message.getPayload())));
        // Process the message and prepare the response
        String responseMessage = "你好, " + message.getHeaders().getReplyChannel() + "!";
        // Send the response only to the client that created the session
        //        log.info("回复消息给{}", sessionId);
        WebSocketSession session = (WebSocketSession) message.getHeaders().get("webSocketSession");
//        messagingTemplate.convertAndSend("/topic/" + sessionId, responseMessage);

//        messagingTemplate.convertAndSendToUser(sessionId, "/topic", responseMessage, headerAccessor.toMap());

        return "request的处理结果";
    }

//    @Secured("ROLE_monitor")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, /*MyAuthenProvider.MyAuth authentication,*/ ModelMap modelMap){
//        log.info("{}", authentication);
        List<Student> students = studentService.queryStudent();
        modelMap.addAttribute("sList", students);
        return "list";
    }

    @ResponseBody
    @GetMapping( "/{id}")
    public Student fetch(@PathVariable Integer id) {
        return studentService.getOne(id);
    }

    @PostMapping("/add")
    public String addOrUpdateStudent(@ModelAttribute Student student, Model map) {
        Integer result = studentService.addStudent(student);
        map.addAttribute("success", result == 1);
        return "index";
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam("id") Integer id){
        studentService.deleteStudent(id);
        return "redirect:list";
    }

    @ResponseBody
    @PutMapping("/update")
    public Integer update(@RequestBody Student student) {
        Integer integer = studentService.updateStudent(student);
        log.info("updated count {}", integer);
        return integer;
    }
    //    @ResponseBody
//    @PostMapping("/upload")
//    public String uploadFiles(@RequestPart("file") MultipartFile mf, HttpServletResponse response)
//            throws FileNotFoundException, IOException {
//        log.info("file name: {}, file type: {}", mf.getOriginalFilename(), mf.getContentType());
//        Streams.copy(mf.getInputStream(),
//                new BufferedOutputStream(new FileOutputStream("/Users/shengjiesong/Documents/" +
//                        mf.getOriginalFilename())),
//                true);
//        response.setStatus(HttpStatus.SC_ACCEPTED);
//        return "file size: " + mf.getSize();
//    }

}
