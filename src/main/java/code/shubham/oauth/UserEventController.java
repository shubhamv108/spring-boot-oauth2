package code.shubham.oauth;

import code.shubham.commons.models.LogMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.lang.Exception;
import io.sentry.Sentry;

@Slf4j
@RestController
@RequestMapping("/user/{userId}/events")
public class UserEventController {

	private final UserEventRepository repository;

	@Autowired
	public UserEventController(final UserEventRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<?> post(@PathVariable("userId") final String userId, @RequestBody final UserEvent event)
			throws InterruptedException, ExecutionException {
		log.info(String.format("[START] Received Request: /user/%s/events; Body: %s", userId, event));

		try {
			throw new Exception("This is a test.");
		}
		catch (Exception e) {
			Sentry.captureException(e);
		}
		event.setUserId(userId);
		final UserEvent persistedAction = this.repository.save(event);
		log.info(LogMessage.of("Persisted event for userId: %s", userId));
		return ResponseEntity.of(Optional.ofNullable(persistedAction));
	}

	@GetMapping
	public ResponseEntity<?> get(@PathVariable("userId") final String userId)
			throws InterruptedException, ExecutionException {
		return ResponseEntity.of(Optional.ofNullable(this.repository.findByUserId(userId)));
	}

}
